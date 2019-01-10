/**
 * 
 */
app.controller('ChatCtrl',function($scope,$rootScope,ChatService){
	$scope.users=[]
	$scope.chats=[]
	$scope.stompClient=ChatService.stompClient//Websocket connection over STOMP protocol
	$scope.userName=$rootScope.user.firstname
	$scope.$on('sockConnected',function(event,frame){
		alert($scope.userName + ' is connected with websocket')
		//send the username to the controller in the middleware
		//receive the list of username's who are already connected with websocket
		//send : username
		//receive :List of usernames
		//call the handler method in SockController 
		$scope.stompClient.subscribe("/app/join/"+$scope.userName,function(message){
			alert(message.body)
			$scope.users=JSON.parse(message.body) //message.body is of Type String, convert it into JSON by using JSON.parse()
			$scope.$apply()
		})
		
		$scope.stompClient.subscribe("/topic/join",function(message){
			user=JSON.parse(message.body)
			$scope.latestUser=user
			alert($scope.latestUser + ' has joined the chat')
			$scope.addUser(user)
		})
	})
	
	$scope.$on('sockConnected',function(event,frame){
		alert('subscribe to the destination /queue/chats  and /queue/chats/'+$scope.userName )
		$scope.stompClient.subscribe("/queue/chats",function(message){//group chat
			$scope.processIncomingMessage(message,true)
		})
		$scope.stompClient.subscribe("/queue/chats/"+$scope.userName,function(message){
			$scope.processIncomingMessage(message,false)
		})//private chat
		
	})
	
	$scope.processIncomingMessage=function(message,broadcast){
		chat=JSON.parse(message.body)//chat in JSON fmt.  to,from and message
		chat.direction='incoming'
		chat.broadcast=broadcast//true for group chat and false for private chat
		if(chat.from!=$scope.userName){
			$scope.addChat(chat)//add the chat to an array
			$scope.$apply()
		}
	}
	
	$scope.addChat=function(chat){
		$scope.chats.push(chat)
	}
	
	$scope.addUser=function(user){
		$scope.users.push(user)
		$scope.$apply()
	}
	
	$scope.sendMessage=function(chat){
		chat.from=$scope.userName
		$scope.stompClient.send("/app/chat",{},JSON.stringify(chat))//stringify is to convert JSON to String
		$scope.$broadcast('sendingChat',chat)
		$scope.chat.message=''
	}
	
	$scope.$on('sendingChat',function(event,message){
		chat=angular.copy(message)
		chat.from='Me'
		chat.direction='outgoing'
		$scope.addChat(chat)
	})
})