/**
 * 
 */
app.controller('BlogInDetailCtrl',function($scope,BlogService,$routeParams,$location,$sce){
	var blogId=$routeParams.blogId
	$scope.isRejected=false
	if($routeParams.blogId!=undefined){
		BlogService.getBlog(blogId).then(function(response){
			//query? select * from blogpost where blogpostid=?
			$scope.blogPost=response.data
			$scope.htmlcontent=$sce.trustAsHtml($scope.blogPost.blogContent)
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
		
		/*BlogService.hasUserLikedBlogPost(blogId).then(
		function(response){
			
			if(response.data=='')
				$scope.isLiked=false
				else
					$scope.isLiked=true
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})*/
	}
	
	$scope.approveblogPost=function(blogPostId){
		BlogService.approveBlog(blogPostId).then(function(response){
			$location.path('/blogswaitingforapproval/0')
		},function(resposne){
			$scope.error=response.data 
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.rejectblogPost=function(blogPost,rejectionReason){
		if(rejectionReason==undefined)
			rejectionReason='Not Mentioned by Admin'
		BlogService.rejectBlog(blogPost,rejectionReason).then(function(response){
			$location.path('/blogswaitingforapproval/0')
		},function(response){
			$scope.error=response.data 
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.showTxtForRejectionReason=function(){
		$scope.isRejected=!$scope.isRejected
	}
		
	$scope.updateBlog=function(blogPost){
		BlogService.updateBlog(blogPost).then(function(response){
			alert('blogpost is updated successfully and it is waiting for approval...')
			$location.path('/blogsapproved/1')
		},function(response){
			$scope.error=response.data 
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	 $scope.updateLikes=function(blogPostId){
  	        BlogService.updateLikes(blogPostId).then(
		    function(response){
	    	$scope.blogPost=response.data
    	    $scope.isLiked=!$scope.isLiked
		    },function(response){
		    	if(response.status==401)
				$location.path('/login')
		   })
	 }
	 $scope.addBlogComment=function(commentTxt,blogPost){
		   var blogComment={}
		   blogComment.commentTxt=commentTxt
		   blogComment.blogPost=blogPost
		   console.log(blogComment)
		   BlogService.addBlogComment(blogComment).then(function(response){
			   $scope.blogComment=response.data
			   $scope.commentTxt=""
		   },function(response){
			   if(response.status==401)
					$location.path('/login')
		   })
	   }
		
	   $scope.getBlogComments=function(blogPostId){
		   BlogService.getBlogComments(blogPostId).then(
		   function(response){
			   $scope.comments=response.data //it is List<BlogComment> 
		   },function(response){
			   if(response.status==401)
					$location.path('/login')
		   })
	   }
})