<%@page import="models.Perfume"%>
<%@page import="models.Picture"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/inc/header.jsp" %>
<%@include file="/templates/public/inc/slide.jsp"%>
  <section class="section">
    <div class="container">
    <%
      	if(request.getAttribute("perCatList") != null){
      		List<Perfume> perCatList = (List<Perfume>) request.getAttribute("perCatList");
      		if(perCatList.size() > 0){
      			%>
      <div class="row">
      <%
      	for(Perfume perCat : perCatList){
      		%>
      	<div class="col-sm-4">
          <div class="text-center">
            <div class="ad-box">
              <div class="ad-box-text">
                <p class="text-left"><%=perCat.getCatPer().getCatPerfume() %></p>
                <h4><%=perCat.getPerfumes() %></h4>
                <p class="text-right">Khuyến mãi</p>
                <span class="tick"><i class="flaticon-arrowhead7"></i></span>
              </div><!-- /ad-box-inner -->
              <%
              	if(request.getAttribute("picPerCat"+perCat.getId()) != null){
              		Picture picPerCat = (Picture) request.getAttribute("picPerCat"+perCat.getId());
              		%>
              <figure>
              	<img src="<%=request.getContextPath()%>/uploads/images/product/<%=picPerCat.getPicture()%>"alt="image"style="width:200px;height:200px;">
              </figure>
              		<%
              	}else{
              		%>
              <figure>
              	<img src="<%=request.getContextPath()%>/templates/public/images/noimage.gif "alt="image"style="width:200px;height:200px;">
              </figure>
              		<%
              	}
              %>
              <a href="<%=request.getContextPath()%>/productsingle?id=<%=perCat.getId()%>"></a>
            </div><!-- /ad-box -->
          </div>
        </div>
      		<%
      	}
      %>
      </div>
      			<%
      		}
      	}
      %>
      

      <div class="features-list-box">
        <ul class="feautes-list">
          <li><a href="javascript:void(0)">Giá Khuyến mãi</a></li>
          <li><a href="javascript:void(0)">Ưu đãi giành riêng bạn</a></li>
          <li><a href="javascript:void(0)">Thương hiệu độc quyền</a></li>
          <li><a href="javascript:void(0)">Đặt hàng là có</a></li>
          <li><a href="javascript:void(0)">Ship hàng nhanh</a></li>
        </ul><!-- /features-list -->
      </div><!-- /features-list-box -->
<%
	if(request.getAttribute("perfumeList") != null){
		List<Perfume> perfumeList = (List<Perfume>) request.getAttribute("perfumeList");
		if(perfumeList.size() > 0){
			%>
	<div class="row">
	<%
		for(Perfume objEVA: perfumeList){
			%>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail thumbnail-product">
			<%
				if(request.getAttribute("pic" + objEVA.getId()) != null){
					Picture pic = (Picture) request.getAttribute("pic" + objEVA.getId());
					String picture = pic.getPicture();
						%>
				<figure class="image-zoom">
	              <img src="<%=request.getContextPath()%>/uploads/images/product/<%=picture%>" alt="image" style="width:200px;height:250px;">
	            </figure>
						<%
				}else{
					%>
				<figure class="image">
					<img src="<%=request.getContextPath()%>/templates/public/images/noimage.gif" alt="image">
				</figure>
					<%
				}
			%>
				
				<div class="caption text-center">
					<h5><a href="<%=request.getContextPath()%>/productsingle?id=<%=objEVA.getId()%>"><%=objEVA.getPerfumes() %></a></h5>
					<div class="rating-star">
						<i class="flaticon-favourites7 selected"></i>
						<i class="flaticon-favourites7 selected"></i>
						<i class="flaticon-favourites7 selected"></i>
						<i class="flaticon-favourites7"></i>
						<i class="flaticon-favourites7"></i>
					</div><!-- /rating-star -->
					<p class="prod-price text-primary">$<%=objEVA.getMoney()%></p>
					<div class="row">
						<div class = "col-md-5">
							<button idPro="<%=objEVA.getId() %>" id="idpro-order"type="button" class="btn btn-info idpro-order">
								Giỏ hàng
							</button>
						</div>
						<div class = "col-md-5">
							<button type="button" class="btn btn-success">
							<a href="<%=request.getContextPath()%>/addtocart?id=<%=objEVA.getId()%>">Mua ngay</a>
							</button>
						</div>
					</div>
				</div>
		  </div><!-- /thumbail -->
		</div>
			<%
		}
	%>
      </div>
			<%
		}
	}
%>
<%
	if(request.getAttribute("proNews") != null){
		List<Perfume> proNews = (List<Perfume>) request.getAttribute("proNews");
		if(proNews.size() > 0){
			for(Perfume objProNew : proNews){
				%>
		<div class="tips-bar bg-primary">
			<p>Sản phẩm mới&nbsp;<strong><%=objProNew.getPerfumes() %>. </strong><%=objProNew.getDescription()%>&nbsp;<strong>$<%=objProNew.getMoney() %></strong></p>
			<a href="<%=request.getContextPath()%>/productsingle?id=<%=objProNew.getId() %>" class="read-more">Đọc thêm</a>
		</div> <!-- /tips-bar -->
				<%
			}
		}
	}
%>
		 
<%
	if(request.getAttribute("perfumeListView") != null){
		List<Perfume> perfumeListView = (List<Perfume>)request.getAttribute("perfumeListView");
		if(perfumeListView.size() > 0){
			%>
		<header class="heading">
			<span class="caro-prev caro-featured-prev"><i class="flaticon-arrowhead7"></i></span>
			<span class="caro-next caro-featured-next"><i class="flaticon-arrow487"></i></span>
			<h4>Nước hoa nổi bật</h4>
		</header>
			<%
			for(Perfume objPFLView : perfumeListView){
				%>
		<div class="carousel-single owl-carousel" data-trans="backSlide" data-btn-next="caro-featured-next" data-btn-prev="caro-featured-prev">
			<div class="item">
				<div class="featured-box">
					<div class="row">
						<div class="col-sm-5">
						<%
							if(request.getAttribute("picView"+objPFLView.getId()) != null){
								Picture picView = (Picture)request.getAttribute("picView"+objPFLView.getId());
								String picV = picView.getPicture();
								%>
							<figure class="image">
								<img src="<%=request.getContextPath()%>/uploads/images/product/<%=picV %>" alt="image">
							</figure>
								<%
							}else{
								%>
							<figure class="image">
								<img src="<%=request.getContextPath()%>/templates/public/images/noimage.gif" alt="image">
							</figure>
								<%
							}
						%>
						</div>
						<div class="col-sm-7">
							 <div class="text">
								<p>Thương hiệu: <a href="product-single.html" class="text-primary"><%=objPFLView.getBrand()%></a></p>
								<h4>Tên nước hoa: <%=objPFLView.getPerfumes()%></h4>
								<p><a href="<%=request.getContextPath()%>/productsingle?id=<%=objPFLView.getId()%>" class="read-more text-primary">Xem thông tin</a></p>
								<p><a href="#info-1" data-toggle="collapse">Khi nào tôi mới nhận được hàng, nếu tôi đặt ngay bây giờ? <i class="flaticon-small63"></i></a></p>
								  <div id="info-1" class="collapse in">
	               					<p>Sau 2 ngày sẽ có hàng</p>
									<p>Thời gian chỉ là gần đúng, ước tính theo thời gian giao hàng trước đó của các công ty vận chuyển.</p>
									<a href="javascript:void(0)" idPro="<%=objPFLView.getId() %>" class="btn btn-default idpro-order">Thêm vào giỏ hàng</a>
								  </div>
               				</div>
						</div>
					</div>
					
				</div><!-- /featured-box -->
			</div>
		</div>
		<%
				}
			%>
		<%
		}
	}
%>
    </div><!-- /container -->
  </section><!-- /section -->

	<section class="section-sales section-image" style="background-image:url(<%=request.getContextPath()%>/templates/public/images/resource/bgImg-1.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-sm-9 col-sm-push-3 col-md-6 col-md-push-6 col-lg-5 col-lg-push-7 ">
					<div class="promotion-box">
						<div class="text">
							<h4>Nước rửa mặt</h4>
							<h3>Giảm giá</h3>
							<h4>Lên đến <strong>70%</strong></h4>
						</div>
						<a href="promotions.html" class="btn btn-default text-uppercase">Mua ngay</a>
					</div><!-- /promotion-box -->
				</div>
			</div>
		</div>
	</section>

<script>
    document.getElementById("home").classList.add('active-menu');
</script>
<script>
$(document).on("click", ".idpro-order", (function () {
	var idpro = $(this).attr("idpro");
	//lấy dữ liêu
	swal.fire({
        title: 'Thêm sản phẩm vào giỏ hàng',
        text: "Dữ liệu sẽ được cập nhật!",
        icon: 'warning',
        padding: '3em',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Đồng ý!',
        cancelButtonText: 'Hủy bỏ',
        reverseButtons: true,
        customClass: null
    }).then((result) => {
    	  if (result.value) {
    		  $.ajax({
    			  	async: false,
    	            type: "GET",
    	            url: "<%=request.getContextPath()%>/addtocart",
    	            data: {id: idpro},
    	            success: function (data) {
    	            	if(data==false){
	    	            		Swal.fire(
   	    	                    'Thông Báo!',
   	    	                    'Không thành công.',
   	    	                    'danger'
   	    	                ).then(function () {
   	    	                    location.reload();
   	    	                })
    	            	}else{
   	            			Swal.fire(
	            			      'Thành công!',
	            			      'Thêm vào giỏ hàng thành công.',
	            			      'success'
	            			    ).then(function () {
   	    	                    	location.reload();
   	    	                })
    	            	}
    	            },
    	            error: function (xhr, ajaxOptions, thrownError) {
    	                Swal.fire(
    	                    'Thông Báo!',
    	                    'Thao tác không thể thực hiện.',
    	                    'danger'
    	                ).then(function () {
    	                    location.reload();
    	                })
    	            }
    	        });
    		  } else if (result.dismiss === Swal.DismissReason.cancel) {
    		    swal.fire(
    		      'Thất bại',
    		      'Bạn Chưa thực hiện thao tác này',
    		      'error'
    		    )
    		  }
    		})
}))
</script>
<%@include file="/templates/public/inc/footer.jsp" %>