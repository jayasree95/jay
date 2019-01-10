/**
 * 
 */
app.filter('reverse', function() {
	  return function(items) {
	    return items.slice().reverse();
	  };
	});

	app.directive('ngFocus', function() {
	  return function(scope, element, attrs) {
	    element.bind('click', function() {
	      $('.' + attrs.ngFocus)[0].focus();
	    });
	  };
	});
app.factory('ChatService',function($rootScope){
	var socket=new SockJS("/middleware/chatmodule")
	var stompClient=Stomp.over(socket)
	stompClient.connect('','',function(frame){//once the browser connects with the websocket, frame object as a response
		alert('Inside Connection function in ChatService')
		alert('Opening a connection with websocket')
		$rootScope.$broadcast('sockConnected',frame)
	})
	return{
		stompClient:stompClient//Websocket connection over Stomp protocol
	}
})
