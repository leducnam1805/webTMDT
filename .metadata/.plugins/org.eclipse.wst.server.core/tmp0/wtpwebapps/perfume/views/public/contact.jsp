<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/inc/header.jsp" %>

  <section class="section-banner">
    <div class="container">
      <ul class="breadcrumb">
        <li><a href="#">Trang chủ</a></li>
        <li class="active">Liên hệ</li>
      </ul>
    </div>
  </section><!-- /section-banner -->

  <section class="section-compact">
    <div class="container">
      <div class="cart-block">
        <header class="heading-3 page-heading">
          <h4>Câu hỏi? Kích vào đây để xem các câu hỏi thường gặp <span class="text-primary">FAQ</span></h4>
        </header>
        
        <div class="form-main-container">
        <%
		if(!"".equals(request.getParameter("msg"))){
			String msg = request.getParameter("msg");
			if("OK".equals(msg)){
				%>
					<div class="alert alert-success" role="alert">
						 Cảm ơn bạn đã phản hồi cho chúng tôi. Chúc bạn có một ngày tràn đầy niềm vui.!
					</div>
				<%
			}
		}
	%>
          <div class="row">
            <div class="col-sm-5 col-lg-4">
              <header class="heading-5">
                <h4>01/</h4>
                <span>Liên hệ <br>Chúng tôi</span>
              </header>
              <ul class="contact-list">
                <li><span class="iconic"><i class="flaticon-call36"></i></span>0797 - 207 - 493</li>
                <li><span class="iconic"><i class="flaticon-email15"></i></span><a href="#">leducnam1805@gmail.com</a></li>
                <li><span class="iconic"><i class="flaticon-twitter16"></i></span><a href="#">@followperfume</a></li>
                <li><span class="iconic"><i class="flaticon-facebook43"></i></span><a href="#">facebook.com/perfume</a></li>
              </ul>
            </div>
            <div class="col-sm-7 col-lg-8">
              <div class="form-section-box">
                <header class="heading-5 text-center">
                  <h4>02/</h4>
                  <span>Gửi liên hệ <br>cho chúng tôi</span>
                </header>

                <form class="checkout-form" method="post" action="<%=request.getContextPath()%>/contact">
                <%
		        	if(request.getAttribute("err") != null){
		        		%>
		        			<div class="alert alert-danger" role="alert">
								  ${err}
							</div>
		        		<%
		        	}
		        %>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Tên*</label>
                    <div class="col-md-10">
                      <input type="text" value="${name}"class="form-control" placeholder="Name" name="name">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Email</label>
                    <div class="col-md-10">
                      <input type="email" class="form-control" placeholder="Email Address"name="email">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Số liên lạc*</label>
                    <div class="col-md-10">
                      <input type="text" class="form-control" placeholder="Phone" name="phone">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Lời nhắn</label>
                    <div class="col-md-10">
                      <textarea class="form-control" placeholder="Comments" name="message"></textarea>
                    </div>
                  </div>
                  <p class="text-center"><input type="submit" class="btn btn-default btn-lg" value="Gửi"></p>
                </form>
              </div><!-- /form-section-box -->
            </div>
          </div>
        </div><!-- /form-main-container -->

      </div><!-- /cart-block -->
    </div>

  </section>

	<section class="section-focus bg-dark">
		<div class="container">
			
			<div class="promo-box">
				<div class="row">
					<div class="col-sm-9">
						<p><strong>Phần thưởng</strong> - Tham gia đánh giá sản phẩm để nhận phần thưởng</p>
					</div>
					<div class="col-sm-3">
						<a class="btn btn-default btn-lg">Bắt đầu đánh giá</a>
					</div>
				</div>
			</div>
		</div>
	</section>

<%@include file="/templates/public/inc/footer.jsp" %>