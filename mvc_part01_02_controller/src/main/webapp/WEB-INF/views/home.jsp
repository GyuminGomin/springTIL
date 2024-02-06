<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" pageEncoding="utf-8" %>
<html>
<head>
	<title>Home</title>
	<meta charset="utf-8">
</head>
<body>
<h1>
	Hello world! - ${sessionScope.test}  
</h1>
<h2>
	model : ${requestScope.modelTest}
</h2>
<h3><a href="doA">doA</a></h3>
<h3><a href="doB">doB</a></h3>
<h3><a href="doC?msg=helloSpring">doC</a></h3>
<h3><a href="doD?msg=helloSpring">doD</a></h3>
<h3><a href="doD">none-parameter-doD</a></h3>
<hr/>
<a href="doF">doF GET</a> <br/>
<form action="doF" method="POST">
	<input type="number" name="age" required />
	<input type="text" name="name" required />
	<button>doF POST</button>
</form>
<hr/>
<h1>상품 정보 입력</h1>
<form action="productWrite" method="POST">
	<input type="text" name="name" placeholder="상품이름" required /> <br/>
	<input type="number" name="price" placeholder="상품가격" required /> <br/>
	<button>PRODUCT WRITE</button>
</form>
<hr/>
<form action="productWriteSubmit" method="POST">
	<input type="text" name="name" placeholder="상품이름" required /> <br/>
	<input type="number" name="price" placeholder="상품가격" required /> <br/>
	<button>PRODUCT WRITE SUBMIT</button>
</form>
<hr/>
<h3><a href="redirect">redirect</a></h3>
</body>
</html>