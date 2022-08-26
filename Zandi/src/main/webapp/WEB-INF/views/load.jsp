<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
	#load {
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    position: fixed;
    display: block;
    opacity: 0.8;
    background: white;
    z-index: 99;
    text-align: center;
	}

	#load > img {
	    position: absolute;
	    top: 50%;
	    left: 50%;
	    z-index: 100;
	}
	
	.hide{
		visibility: hidden;
	}
</style>
<script>
	window.onload = function() {
		const load = document.querySelector('#load');
	    load.classList.add("hide");
	};
</script>
</head>
<body>
	<div id="load">
		<img src="${rootPath}/static/image/loading.png" alt="loading">
	</div>

</body>
</html>