<%@page import="daos.CatPerfumeDao"%>
<%@page import="util.CategoriesUtil"%>

<%@page import="models.CatPerfume"%>
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
            <h1 class="m-0">Thương hiệu</h1>
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
	<!-- Main content -->
	<div class="container">
  <h2>Danh sách thương hiệu</h2>
  <br>
  <!-- Nav tabs -->
  <!-- <ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" data-toggle="tab" href="#home">Thương hiệu</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-toggle="tab" href="#menu1">Loại thương hiệu</a>
    </li>
  </ul> -->

  <!-- Tab panes -->
  <div class="tab-content">
  <!-- start thương hieu -->
    <div id="home" class="container tab-pane active card"><br>
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
    	if(request.getAttribute("catPFList") != null){
    		List<CatPerfume> catPFList = (List<CatPerfume>)request.getAttribute("catPFList");
    		if(catPFList.size() > 0){
    			%>
    	<section class="content">
	      <div class="container-fluid">
	        <table class="table table-striped table-bordered">
			  <thead>
			    <tr>
			      <th scope="col">STT</th>
			      <th scope="col">Thương hiệu</th>
			      <th scope="col">Loại thương hiệu</th>
			      <th scope="col">
			      	Chức năng &emsp;
			      	<a href="<%=request.getContextPath()%>/admin/catperfume/add" class="btn btn-success btn-md">Thêm</a>
			      </th>
			    </tr>
			  </thead>
			  <tbody>
			  <%
			  int i = 0;
			  	for(CatPerfume objCat : catPFList){
			  		i++;
			  		int id = objCat.getId();
			  		%>
			  	<tr>
			      <th scope="row"><%=i %></th>
			      <td><%=objCat.getCatPerfume() %></td>
	    		  <td>
		    		  <%
		    		  	if(objCat.getParrent_id() != 0){
		    		  		CatPerfume catP = new CatPerfumeDao().viewID(objCat.getParrent_id());
		    		  %>
		    		  			<%=catP.getCatPerfume()%>
		    		  		<%
		    		  	} else {
		    		  		out.print("");
		    		  	}
		    		  %>
	    		  </td>
			      <td>
					<a href="<%=request.getContextPath()%>/admin/catperfume/edit?id=<%=id %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
				  	<a catPF=<%=id%> class="btn btn-danger del-catperfume"><i class="fa fa-trash" aria-hidden="true"></i> Xóa</a>
				  </td>
			    </tr>
			  		<%
			  	}
			  %>
			  </tbody>
			</table>
	        <!-- Main row -->
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
    </div><!-- end thương hieu -->
  </div>
</div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<script>
$(document).on("click", ".del-catperfume", (function () {
	var id = $(this).attr("catPF");
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
    	            url: "<%=request.getContextPath()%>/admin/catperfume/del",
    	            data: {id : id},
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