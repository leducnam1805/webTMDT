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
            <h1 class="m-0">Quản lý Blog</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Blog</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
	      <div class="col-sm-12">
            <form method="GET" action="<%=request.getContextPath()%>">
                 <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning" style="float:right" />
                 <input type="search" name="nameCat" value=""class="form-control" placeholder="Nhập loại nước hoa" style="float:right; width: 300px;" />
                 <div style="clear:both"></div>
             </form>
          </div><!-- /.col -->
        </div><br></br>
        <!-- /.row -->
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		      <th scope="col">Tên khách hàng</th>
		      <th scope="col">Email</th>
		      <th scope="col">Phone</th>
		      <th scope="col">Messgae</th>
		      <th scope="col">Chức năng</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row">1</th>
		      <td>Nam</td>
		      <td>n@gmail.com</td>
		      <td>0797207493</td>
		      <td>Lời nhăn</td>
		      <td>
		      	<a href="" class="btn btn-success btn-md">Xem</a>
		      </td>
		    </tr>
		  </tbody>
		</table>
        <!-- Main row -->
        <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item disabled">
		      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trang trước</a>
		    </li>
		    <li class="page-item"><a class="page-link" href="#">1</a></li>
		    <li class="page-item"><a class="page-link" href="#">2</a></li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item">
		      <a class="page-link" href="#">Trang tiếp</a>
		    </li>
		  </ul>
		</nav>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
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