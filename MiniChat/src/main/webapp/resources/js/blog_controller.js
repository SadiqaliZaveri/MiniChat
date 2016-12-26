'use strict';
 
app.controller('BlogController', ['$scope', 'BlogService', function($scope, BlogService) {
    var self = this;
    self.blog={blogId:null,blogName:'',blogContent:''};
    self.blogs=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllBlogs();
 
    function fetchAllBlogs(){
        BlogService.fetchAllBlogs()
            .then(
            function(d) {
                self.blogs = d;
            },
            function(errResponse){
                console.error('Error while fetching blogs');
            }
        );
    }
 
    function createBlog(blog){
        BlogService.createBlog(blog)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while creating blog');
            }
        );
    }
 
    function updateBlog(blog, blogId){
        BlogService.updateBlog(blog, blogId)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while updating blog');
            }
        );
    }
 
    function deleteBlog(blogId){
        BlogService.deleteBlog(blogId)
            .then(
            fetchAllBlogs,
            function(errResponse){
                console.error('Error while deleting Blog');
            }
        );
    }
 
    function submit() {
        if(self.blog.blogId===null){
            console.log('Saving New Blog', self.blog);
            createBlog(self.blog);
        }else{
            updateBlog(self.blog, self.blog.blogId);
            console.log('Blog updated with id ', self.blog.blogId);
        }
        reset();
    }
 
    function edit(blogId){
        console.log('id to be edited', blogId);
        for(var i = 0; i < self.blogs.length; i++){
            if(self.blogs[i].blogId === blogId) {
                self.blog = angular.copy(self.blogs[i]);
                break;
            }
        }
    }
 
    function remove(blogId){
        console.log('id to be deleted', blogId);
        if(self.blog.blogId === blogId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteBlog(blogId);
    }
 
 
    function reset(){
        self.blog={id:null,blogName:'',blogContent:''};
        $scope.blogForm.$setPristine(); //reset Form
    }
 
}]);