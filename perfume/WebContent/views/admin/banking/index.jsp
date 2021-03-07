<%@page import="models.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
  <%@include file="/templates/admin/inc/header.jsp" %>
  <%@include file="/templates/admin/inc/sidebar.jsp" %>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Quản lý tài chính</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Tài chính</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
<%
	if(request.getAttribute("listItem") != null){
		List<Item> listItem = (List<Item>) request.getAttribute("listItem");
		if(listItem.size() > 0){
			Long totalPrice = 0L;
			Long totalMoney = 0L;
			%>
	<section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
	      <div class="col-sm-12">
            <h3>Doanh thu tháng 12</h3>
            <%
            	for(Item objItem : listItem){
            		Long price = objItem.getPrice();
            		int quantity = objItem.getQuantity();
            		Long money = price * quantity;
            		totalPrice += money;
            	}
            %>
           <h4>Tổng doanh thu: <%=totalPrice %> VNĐ</h4>
          </div><!-- /.col -->
        </div><br></br>
        <!-- /.row -->
        <h4>Chi tiết</h4>
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		      <th scope="col">Tên sản phẩm</th>
		      <th scope="col">Số Lượng</th>
		      <th scope="col">Giá tiền</th>
		      <th scope="col">Tổng tiền</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  int i = 0;
		  	for(Item objItem : listItem){
		  		i++;
		  		Long price = objItem.getPrice();
        		int quantity = objItem.getQuantity();
        		Long money = price * quantity;
        		totalMoney += money;
		  		%>
  			<tr>
		      <th scope="row"><%=i %></th>
		      <td><%=objItem.getProduct().getPerfumes() %></td>
		      <td><%=objItem.getQuantity()%></td>
		      <td><%=price%></td>
		      <td><%=money %></td>
		    </tr>
		  		<%
		  	}
		  %>
		  <tr class="bg-info">
		      <td colspan="4"class="text-center">Thành tiền</td>
		      <td><%=totalMoney%></td>
		    </tr>
		  </tbody>
		</table>
        <!-- Main row -->
        
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
			<%
		}
	}
%>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<script>
$(document).on("click", ".del-catperfume", (function () {
	//lấy dữ liêu
	swal.fire({
        title: 'Bạn Có Chắc Chắn?',
        text: "Dữ Liệu Sẽ Không Thể Phục Hồi!",
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
    	            type: "get",
    	            url: "",
    	            data: {},
    	            success: function (data) {
    	            	if(data==false){
	    	            		Swal.fire(
   	    	                    'Thông Báo!',
   	    	                    'Bạn không thể xóa loại tài khoản này.',
   	    	                    'danger'
   	    	                ).then(function () {
   	    	                    location.reload();
   	    	                })
    	            	}else{
   	            			Swal.fire(
	            			      'Thành công!',
	            			      'Bạn đã xóa thành công.',
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
    		    swalWithBootstrapButtons.fire(
    		      'Thất bại',
    		      'Bạn Chưa thực hiện xóa',
    		      'error'
    		    )
    		  }
    		})
}))
</script>
  <%@include file="/templates/admin/inc/footer.jsp" %>