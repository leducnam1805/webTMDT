<%@page import="models.Item"%>
<%@page import="java.util.List"%>
<%@page import="models.Perfume"%>
<%@page import="models.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/inc/header.jsp" %>

  <section class="section-banner">
    <div class="container">
      <ul class="breadcrumb">
        <li><a href="#">Trang chủ</a></li>
        <li class="active">Giỏ hàng</li>
      </ul>
    </div>
  </section><!-- /section-banner -->

  <section class="section-compact">
    <div class="container">
    <%
		if(!"".equals(request.getParameter("msg"))){
			String msg = request.getParameter("msg");
			if("SUCCESS".equals(msg)){
				%>
					<div class="alert alert-success" role="alert">
						 Đặt hàng thành công.!
					</div>
				<%
			}
		}
	%>
    <%
	if(session.getAttribute("order") != null){
		Order order = (Order) session.getAttribute("order");
		List<Item> ListItem = (List<Item>) order.getItem();
			if(ListItem.size() > 0){
					%>
			<div class="cart-block">
		        <header class="heading-3 page-heading">
		          <div class="row">
		          	<table class="table table-bordered">
					  <thead>
					    <tr>
					      <th scope="col"class="text-center"></th>
					      <th scope="col"class="text-center">Tên sản phẩm</th>
					      <th scope="col"class="text-center">Giá tiền</th>
					      <th scope="col"class="text-center">Số lượng</th>
					      <th scope="col"class="text-center">Thành tiền</th>
					    </tr>
					  </thead>
					  <tbody>
					  <%
					  Long allTong = 0L;
					  int i = 0;
						for(Item item : ListItem){
							i++;
							Long tong = 0L;
							int quanity = item.getQuantity();
							Long price = item.getProduct().getMoney();
							tong = quanity * price;
							allTong += tong;
							int idOrder = item.getId();
							%>
							<tr>
						      <th scope="row" class="text-center">
						      	<a idOrder=<%=idOrder %> title="" class="btn btn-danger del-order"><i class="fa fa-trash" aria-hidden="true"></i> Xóa</a>
						      </th>
						      <td><%=item.getProduct().getPerfumes() %></td>
						      <td><%=item.getPrice()%></td>
						      <td><%=item.getQuantity() %></td>
						      <td><%=tong %></td>
						    </tr>
						    <%
						}
						%>
						<tr>
					      <th colspan="4" class="text-center">Tổng tiền:</th>
					      <td class="text-center"><%=allTong %></td>
					    </tr>
						<%
					  %>
					    
					  </tbody>
					</table>
		          </div>
		        </header>
		      </div>
		      	<%
				}
			}
	%>
        
        <div class="form-main-container">
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
            <%
            	if(session.getAttribute("userInfor") != null){
            		User userInfor = (User) session.getAttribute("userInfor");
            		%>
           <div class="col-sm-7 col-lg-8">
              <div class="form-section-box">
                <header class="heading-5 text-center">
                  <h4>02/</h4>
                  <span>Thông tin <br>đơn hàng</span>
                </header>

                <form class="checkout-form" method="post" action="<%=request.getContextPath()%>/order">
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Số điện thoại*</label>
                    <div class="col-md-10">
                      <input type="text" class="form-control" placeholder="phone" name="phone">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Họ tên</label>
                    <div class="col-md-10">
                      <input type="hidden" value="<%=userInfor.getId()%>"class="form-control" placeholder="Name" name="userID">
                    </div>
                    <div class="col-md-10">
                      <input type="text" value="<%=userInfor.getFullname()%>"class="form-control" placeholder="Name" name="username">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Email</label>
                    <div class="col-md-10">
                      <input type="email" class="form-control" placeholder="Email" name="email">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Địa chỉ nhận hàng(Ghi rõ số nhà, tên đường)</label>
                    <div class="col-md-10">
                      <textarea class="form-control" placeholder="Adress" name="adress"></textarea>
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-md-2 label-md">Ghi chú cho đơn hàng</label>
                    <div class="col-md-10">
                      <textarea class="form-control" placeholder="Note" name="note"></textarea>
                    </div>
                  </div>
                  <div class="form-group row">
                  	<div class="col-md-6">
                      <input type="submit" class="btn btn-info form-control" value="Thanh toán COD">
                    </div>
                    <div class="col-md-6">
                     <input type="submit" class="btn btn-success form-control" value="Thanh toán Online">
                    </div>
                  </div>
                  
                </form>
              </div><!-- /form-section-box -->
            </div>
            		<%
            	}
            %>
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
	
	<script>
$(document).on("click", ".del-order", (function () {
	var idOrder = $(this).attr("idOrder");
	//lấy dữ liêu
	swal.fire({
        title: 'Bạn Có Chắc Chắn?',
        text: "Sản phẩm sẽ bị xóa khỏi giỏ hàng.!",
        icon: 'warning',
        padding: '3em',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Đồng Ý, Xóa!',
        cancelButtonText: 'Hủy bỏ',
        reverseButtons: true,
        customClass: null
    }).then((result) => {
    	  if (result.value) {
    		  $.ajax({
    			  	async: false,
    	            type: "post",
    	            url: "<%=request.getContextPath()%>/addtocart",
    	            data: {idOrder:idOrder},
    	            success: function (data) {
    	            	if(data==false){
	    	            		Swal.fire(
   	    	                    'Thông Báo!',
   	    	                    'Bạn không thể xóa sản phẩm này này.',
   	    	                    'danger'
   	    	                ).then(function () {
   	    	                    location.reload();
   	    	                })
    	            	}else{
   	            			Swal.fire(
	            			      'Thành công!',
	            			      'Bạn đã xóa thành công một sản phẩm.',
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
    		      'Bạn Chưa thực hiện xóa',
    		      'error'
    		    )
    		  }
    		})
}))
</script>

<%@include file="/templates/public/inc/footer.jsp" %>