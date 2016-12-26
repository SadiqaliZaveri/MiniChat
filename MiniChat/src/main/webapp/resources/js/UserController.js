'use strict';

Caller.controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={userId:null,username:'',password:''};
    self.users=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllUsers();
    
    $scope.sort = function(keyname) {
        $scope.sortKey = keyname; //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
      };
    
    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching users');
            }
        );
    }
 
    function createUser(user){
    	UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating user');
            }
        );
    }
 
    function updateUser(user, userId){
    	UserService.updateUser(user, userId)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating user');
            }
        );
    }
 
    function deleteUser(userId){
    	UserService.deleteUser(userId)
    	
            .then(
            fetchAllUsers,
            console.log('User with id ', userId,' deleted'),
            
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }
 
    function submit() {
        if(self.user.userId===null){
            console.log('User Saved', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.userId);
            console.log('User with id ', self.user.userId,' updated');
        }
        reset();
    }
 
    function edit(userId){
        console.log('User with id ', userId,' in update process');
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].userId === userId) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
 
    function remove(userId){
        console.log('User with id ', userId, ' being deleted');
        if(self.user.userId === userId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(userId);
    }
 
 
    function reset(){
    	self.user={userId:null,username:'',password:''};
        $scope.userForm.$setPristine(); //reset Form
    }
 
}]);