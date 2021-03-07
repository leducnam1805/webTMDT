<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="newsletter-holder">
  <div class="offer-tip">
    <div class="offer-tip-inner">
      <span>Khuyến Mãi</span>
      <h1>60%</h1>
      <span></span>
    </div>
  </div><!-- /offer-tip -->
<div class="newsletter-head text-right">
		<h4>Nước hoa cao cấp</h4>
		<h3>Giá chiết khấu</h3>
	</div><!-- /newsletter-head -->
	<div class="newsletter-content">
		<h4 class="text-uppercase">Tất cả những gì bạn cần có thể tìm thấy ở đây</h4>
		<div class="row">
			<div class="col-sm-7">
				<div class="image">
					<img src="<%=request.getContextPath()%>/templates/public/images/resource/img-40.jpg" alt="image">
				</div>
			</div>
			<div class="col-sm-5">
				<ul class="list-nl">
					<li><i class="flaticon-shipping"></i>Giao hàng nhanh 2-3 ngày</li>
					<li><i class="flaticon-32"></i>Tư vấn thẩm mỹ miễn phí</li>
					<li><i class="flaticon-parfum1"></i>Hơn 9000 sản phẩm</li>
					<li><i class="flaticon-wallet33"></i>Hơn 170 chi nhánh</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="newsletter-bottom">
		<p>Để lại cho chúng tôi địa chỉ email của bạn và chúng tôi hứa sẽ gửi cho bạn những ưu đãi tốt nhất của chúng tôi:</p>
		<form action="<%=request.getContextPath()%>/home">
			<div class="form-group">
				<input type="email" class="form-control" placeholder="Email address" name="email">
			</div>
			<input type="submit" class="btn btn-primary" value="Đăng ký">
		</form>
	</div>
</div><!-- /newsletter-holder -->
<div class="newsletter-cover"></div>