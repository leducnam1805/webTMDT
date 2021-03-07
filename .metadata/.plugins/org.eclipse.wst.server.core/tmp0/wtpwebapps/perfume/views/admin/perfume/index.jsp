<%@page import="models.Perfume"%>
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
            <h1 class="m-0">Quản lý nước hoa</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Nước hoa</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
	<%
		if(!"".equals(request.getParameter("msg"))){
			String msg = request.getParameter("msg");
			if("OK".equals(msg)){
				%>
					<div class="alert alert-success" role="alert">
						 Xử lý thành công!
					</div>
				<%
			}
		}
	%>
	<%
		if(request.getAttribute("perList") != null){
			List<Perfume> perList = (List<Perfume>) request.getAttribute("perList");
			if(perList.size() > 0){
				%>
					<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
           <div class="col-sm-6">
               <a href="<%=request.getContextPath()%>/admin/perfume/add" class="btn btn-success btn-md">Thêm</a>
           </div>
	      <div class="col-sm-6">
            <form method="GET" action="<%=request.getContextPath()%>" class="form-inline">
				<select id="cars" class="btn-sm form-control">
				  <option value="0">--- chọn ---</option>
				  <option value="saab">Saab</option>
				  <option value="vw">VW</option>
				  <option value="audi">Audi</option>
				</select>
                 <input type="search" name="nameCat" value=""class="form-control" placeholder="Nhập tên nước hoa" style="float:right; width: 300px;" />
                 <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning" style="float:right" />
                 <div style="clear:both"></div>
             </form>
          </div><!-- /.col -->
        </div><br></br>
        <!-- /.row -->
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		      <th scope="col">Tên nước hoa</th>
		      <th scope="col">Thương hiệu</th>
		      <th scope="col">Loại nước hoa</th>
		      <th scope="col">Số lượng</th>
		      <th scope="col">Giá cả</th>
		      <th scope="col">Chức năng</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  	int i = 0;
		  	for(Perfume objPer : perList){
		  		i++;
		  		int idPF = objPer.getId();
		  		String per = objPer.getPerfumes();
		  		String brand = objPer.getBrand();
		  		String catPer = objPer.getCatPer().getCatPerfume();
		  		int amount = objPer.getAmount();
		  		Long money = objPer.getMoney();
		  		%>
		  			<tr>
				      <th scope="row"><%=i %></th>
				      <td><%=per %></td>
				      <td><%=brand %></td>
				      <td><%=catPer %></td>
				      <td><%=amount %></td>
				      <td>$<%=money %></td>
				      <td>
				      	<a href="<%=request.getContextPath()%>/admin/perfume/views?id=<%=idPF %>" title="" class="btn btn-info"><i class="fa fa-edit "></i> Xem</a>
						<a href="<%=request.getContextPath()%>/admin/perfume/edit?id=<%=idPF %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
					  	<a id=<%=idPF%> title="" class="btn btn-danger del-PF"><i class="fa fa-trash" aria-hidden="true"></i> Xóa</a>
					  </td>
				    </tr>
		  		<%
		  	}
		  %>
		    
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
				<%
			}else{
				%>
					<div class="alert alert-danger" role="alert">
					  Không có dữ liệu
					</div>
				<%
			}
		}
	%>
    
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<script>
$(document).on("click", ".del-PF", (function () {
	var idPF = $(this).attr("id");
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
    	            url: "<%=request.getContextPath()%>/admin/perfume/del",
    	            data: {id:idPF},
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
    		    swal.fire(
    		      'Thất bại',
    		      'Bạn Chưa thực hiện xóa',
    		      'error'
    		    )
    		  }
    		})
}))
</script>
  <%@include file="/templates/admin/inc/footer.jsp" %>