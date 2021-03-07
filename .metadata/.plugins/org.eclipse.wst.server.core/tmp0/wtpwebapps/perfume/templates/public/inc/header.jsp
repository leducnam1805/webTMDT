<%@page import="models.Item"%>
<%@page import="java.util.List"%>
<%@page import="models.Order"%>
<%@page import="models.User"%>
<%@page import="util.CategoriesUtil"%>
<%@page import="models.CatPerfume"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">

<!-- Mirrored from wow-themes.com/demo/html/perfume/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 19 Aug 2015 06:47:57 GMT -->
<head>
<%@include file="/templates/public/inc/library/libraryHeader.jsp"%>
</head>

<body>

	<div class="pageWrap">

		<%
			if(session.getAttribute("userInfor") != null){
				User userInfor = (User) session.getAttribute("userInfor");
				%>
					<div class="tp-bar">
			<div class="container">
				<ul class="tp-links">
					<li><span class="dropBox-btn">Xin chào &nbsp; <%=userInfor.getFullname() %></span>
					</li>
				</ul>
				<!-- /tp-links -->
			</div>
		</div>
				<%
			}else{
				%>
					<div class="tp-bar">
			<div class="container">
				<ul class="tp-links">
					<li><span class="dropBox-btn">Đăng nhập <i
							class="caret caret-cut"></i></span>
						<div class="dropBox">
							<div class="box-section">
								<h6>Bạn đã có tài khoản - Đăng nhập</h6>
								<form class="accounts-form clearfix" method="post" action="<%=request.getContextPath()%>/login">
									<div class="form-left">
										<div class="form-group">
											<input type="text" class="form-control" name="username"
												placeholder="Email Address" required/>
										</div>
										<div class="form-group">
											<input type="password" class="form-control" name="password"
												placeholder="Password" required/>
										</div>
									</div>
									<input type="submit" class="btn btn-default text-uppercase"
										value="Đăng nhập"/>
								</form>
								<!-- /accounts-form -->
								<p class="help-block">
									<a href="#">Quên tài khoản?</a>
								</p>
							</div>
							<!-- /box-section -->
							<div class="box-section">
								<h6>Chưa có tài khoản - Đăng ký</h6>
								<ul class="list-1">
									<li>Tôi muốn trở thành khách hàng vip</li>
									<li>Tôi muốn thanh toán nhanh</li>
								</ul>
								<form class="accounts-form clearfix" method="post" action="<%=request.getContextPath()%>/signup">
									<div class="form-left">
										<div class="form-group">
											<input type="text" class="form-control" placeholder="Họ và tên" name="fullname"
												required>
										</div>
										<div class="form-group">
											<input type="text" class="form-control" placeholder="Email" name="username"
												required>
										</div>
										<div class="form-group">
											<input type="password" class="form-control" placeholder="mật khẩu" name="password"
												required>
										</div>
									</div>
									<input type="submit" class="btn btn-default text-uppercase"
										value="Đăng ký">
								</form>
								<!-- /accounts-form -->
							</div>
							<!-- /box-section -->
						</div>
						<!-- /dropBox --></li>
				</ul>
				<!-- /tp-links -->
			</div>
		</div>
				<%	
			}
		%>
		
		
		<!-- /tp-bar -->

		<div class="main-bar">
			<div class="logo">
				<a href="index-2.html"><img
					src="<%=request.getContextPath()%>/templates/public/images/logo.png"
					alt="perfume"></a>
			</div>
			<!-- /logo -->
			<div class="user-controls-bar">
				<ul class="user-controls">
					<li><span class="site-search-btn dropBox-btn"><i
							class="flaticon-magnifier56"></i></span>
						<div class="dropBox">
							<div class="box-section">
								<form class="accounts-form clearfix">
									<div class="form-left">
										<div class="form-group">
											<input type="search" class="form-control"
												placeholder="Search Key">
										</div>
									</div>
									<input type="submit" class="btn btn-default text-uppercase"
										value="Tìm kiếm">
								</form>
								<!-- /accounts-form -->
							</div>
							<!-- /box-section -->
						</div>
						<!-- /dropBox --></li>
						<%
							if(session.getAttribute("order") != null){
								Order order = (Order) session.getAttribute("order");
								List<Item> listItem = (List<Item>) order.getItem();
								if(listItem.size() > 0){
									%>
					<li>
						<span class="cart-btn dropBox-btn">
							<i class="flaticon-shopping191"></i>
							<%
								int total = 0;
								for(Item quantity : listItem){
									int totalFinal = 0;
									total += quantity.getQuantity();
									totalFinal += total;
									%>
									<span class="badge"><%=totalFinal %></span>
									<%
								}
							%>
						</span>
						<div class="dropBox">
							<div class="box-section">
								<ul class="cart-info-list">
								<%
									for(Item item : listItem){
										Long tong = 0L;
										int quanity = item.getQuantity();
										Long price = item.getProduct().getMoney();
										tong = quanity * price;
										%>
									<li class="cart-item">
										<div class="cart-item-bx">
											<figure>
												<img
													src="<%=request.getContextPath()%>/templates/public/images/resource/img-1.jpg"
													alt="image">
											</figure>
											<div class="text">
												<h6>
													<a href="#"><%=item.getProduct().getPerfumes() %></a>
												</h6>
												<p>Số Lượng: <%=item.getQuantity() %></p>
												<p><%=item.getProduct().getCapacity() %></p>
												<p>$<%=price %></p>
												<p class="tot">$<%=tong %></p>
											</div>
											<button type="button" class="close">&times;</button>
										</div>
										<!-- /cart-item-bx -->
									</li>
										<%
									}
								%>
								</ul>
								<!--/ cart-info-list -->
								<a href="<%=request.getContextPath()%>/order"
									class="btn btn-dark btn-block dismiss-button">Giỏ hàng</a>
								<p>Giao hàng miễn phí khi mua từ trên 3 sản phẩm</p>
							</div>
							<!-- /cart-info-box -->

							<!-- empty cart message -->
							<!-- <div class="box-section">
              <h6>Your Cart is empty</h6>
              <a href="#" class="btn btn-dark btn-block dismiss-button">Continue Shopping</a>
              <p>Enjoy complimentary shipping on all orders over $75 and also complimentary samples and returns with every order.</p>
            </div> -->
							<!-- /cart-info-box -->
						</div>
						<!-- /dropBox --></li>
									<%
								}
							}
						%>
					
					<li class="toggle-menu">
						<button data-target=".navbar-collapse" data-toggle="collapse"
							class="navbar-toggle" type="button">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</li>
				</ul>
				<!-- /user-controls -->
			</div>
			<!-- /user-controls -->

			<div class="main-nav-bar">
				<nav class="navbar-collapse collapse">
					<ul class="main-nav">
						<li><a href="<%=request.getContextPath()%>/home">Trang chủ</a></li>
						<li>
							<a href="<%=request.getContextPath()%>/home">Thương hiệu</a>
							<ul>
								<%
							if (request.getAttribute("catPFList") != null) {
							ArrayList<CatPerfume> catPFList = (ArrayList<CatPerfume>) request.getAttribute("catPFList");
							if (catPFList.size() > 0) {
								// lấy toàn bộ list, kiểm tra nếu id cha = 0 (cấp cao nhất) => view ra
								for (CatPerfume cat : catPFList) {
							// kiểm tra
							if (cat.getParrent_id() == 0) {
						%>
						<li><a href="<%=request.getContextPath()%>/cat?id=<%=cat.getId()%>"><%=cat.getCatPerfume()%></a>
							<%
								CategoriesUtil.showBrand(cat.getId(), request, out);
							}
							}
							%>
							</ul>
						</li>
						<li><a href="<%=request.getContextPath()%>/blog">Blog</a></li>
						<li><a href="<%=request.getContextPath()%>/contact">Liên
								hệ</a></li>
					</ul>
				</nav>
				<%
					}
				}
				%>
			</div>
			<!-- /main-nav-bar -->
		</div>
		<!-- /main-bar -->