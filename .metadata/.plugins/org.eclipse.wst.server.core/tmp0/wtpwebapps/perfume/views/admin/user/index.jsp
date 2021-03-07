<%@page import="models.CatUser"%>
<%@page import="models.User"%>
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
            <h1 class="m-0">Quản lý người dùng</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Người dùng</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

	<%
		if(request.getAttribute("userList") != null){
			List<User> userList = (List<User>) request.getAttribute("userList");
			if(userList.size() > 0){
				%>
	<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
           <div class="col-sm-6">
               <a href="<%=request.getContextPath()%>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
           </div>
	      <div class="col-sm-6">
            <form method="GET" action="<%=request.getContextPath()%>/admin/user/search" class="form-inline">
            <%
            	if(request.getAttribute("catUserList") != null){
            		List<CatUser> catUserList = (List<CatUser>)request.getAttribute("catUserList");
            		if(catUserList.size() > 0){
            			%>
            			<select id="catUser" class="btn-sm form-control" name="catUser">
	      				  <option value="0">--- Loại tài khoản ---</option>
	      				  <%
	      				  	for(CatUser objCU : catUserList){
	      				  		int id = objCU.getId();
	      				  		String nameCU = objCU.getCatUser();
	      				  		%>
	      				  			<option value="<%=id%>"><%=nameCU %></option>
	      				  		<%
	      				  	}
	      				  %>
	      				</select>
            			<%
            		}
            	}
            %>
                 <input type="search" name="nameUser" value=""class="form-control" placeholder="Nhập tên tài khoản" style="float:right; width: 300px;" />
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
		      <th scope="col">Tên đăng nhập</th>
		      <th scope="col">Tên đầy đủ</th>
		      <th scope="col">Số dư</th>
		      <th scope="col">Loại tài khoản</th>
		      <th scope="col">Chức năng</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  	int i = 0;
		  	for(User objUser : userList){
		  		i++;
		  		int id = objUser.getId();
		  		String username = objUser.getUsername();
		  		String fullname = objUser.getFullname();
		  		String money = objUser.getMoney();
		  		String catUser = objUser.getCatUser().getCatUser();
		  		%>
		  			<tr>
				      <th scope="row"><%=i %></th>
				      <td><%=username %></td>
				      <td><%=fullname %></td>
				      <td>$<%=money%></td>
				      <td><%=catUser %></td>
				      <td>
				      <%
				      	if(id > 1){
				      		%>
				      			<a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=id %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
					  			<a idUser=<%=id%> class="btn btn-danger del-user"><i class="fa fa-trash" aria-hidden="true"></i> Xóa</a>
				      		<%
				      	}
				      %>
					  </td>
				    </tr>
		  		<%
		  	}
		  %>
		  </tbody>
		</table>
        <!-- Main row -->
        <%
  			int numberOfPage = (Integer)request.getAttribute("numberOfPage");
  			int currentPage = (Integer)request.getAttribute("currentPage");
  			if(userList != null || userList.size() > 0){
  			%>
        <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <%
		    	for(int j = 1;j <= numberOfPage;j++){
		    		%>
		    			<%
		    				if(currentPage == j){
		    					%>
		    						<li class="page-item active"><a class="page-link" href="#"><%=j %></a></li>
		    					<%
		    				}else{
		    					%>
	    							<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/user?page=<%=j%>"><%=j %></a></li>
	    						<%
		    				}
		    			%>
		    		<%
		    	}
		    %>
		  </ul>
		</nav>
		<%}
		%>
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
$(document).on("click", ".del-user", (function () {
	var idUser = $(this).attr("idUser");
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
    	            url: "<%=request.getContextPath()%>/admin/user/del",
    	            data: {id : idUser},
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
    		    Swal.fire(
    		      'Thất bại',
    		      'Bạn Chưa thực hiện xóa',
    		      'error'
    		    )
    		  }
    		})
}))
</script>
  <%@include file="/templates/admin/inc/footer.jsp" %>