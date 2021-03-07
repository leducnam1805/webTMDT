<%@page import="models.Permission"%>
<%@page import="models.CatUser"%>
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
            <h1 class="m-0">Phân quyền</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Phân quyền</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

<%
	if(request.getAttribute("catUserList") != null){
		List<CatUser> catUserList = (List<CatUser>) request.getAttribute("catUserList");
		if(catUserList.size() > 0){
			%>
	<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <%-- <div class="row">
          <div class="col-sm-12">
            <form method="GET" action="<%=request.getContextPath()%>">
                 <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning" style="float:right" />
                 <input type="search" name="nameCat" value=""class="form-control" placeholder="Nhập loại tài khoản" style="float:right; width: 300px;" />
                 <div style="clear:both"></div>
             </form>
          </div><!-- /.col -->
        </div><br></br> --%>
        <!-- /.row -->
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		      <th scope="col">Loại tài khoản</th>
		      <th scope="col">Quyền thêm</th>
		      <th scope="col">Quyền Sửa</th>
		      <th scope="col">Quyền Xóa</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  int i = 0;
		  	for(CatUser objCatPermission : catUserList){
		  		i++;
		  		Boolean addquyen = objCatPermission.getAddquyen();
		  		Boolean editquyen = objCatPermission.getEditquyen();
		  		Boolean delquyen = objCatPermission.getDelquyen();
		  		%>
		  			<tr>
		      <th scope="row"><%=i %></th>
		      <td><%=objCatPermission.getCatUser() %></td>
		      <td>
		      	<div class="form-group">
		    		<div class="col-sm-7 col-md-7">
		    			<div class="input-group">
		    				<div class="form-check form-check-inline">
							  <input id="toggle-state" class="toggle-state-add toggle-state-addquyen" type="checkbox" data-toggle="toggle"
							  	<%
							  		if(addquyen == true){
							  			out.print("checked");
							  		}else{
							  			out.print("unchecked");
							  		}
							  	%>
							  	addquyen=<%=objCatPermission.getId()%>
							  >
								<div id="console-event"></div>
							</div>
		    			</div>
		    		</div>
		    	 </div>
		      </td>
		      <td>
		      	<div class="form-group">
		    		<div class="col-sm-7 col-md-7">
		    			<div class="input-group">
		    				<div class="form-check form-check-inline">
							  <input id="toggle-state" class="toggle-state-add toggle-state-editquyen" type="checkbox" data-toggle="toggle"
							  <%
							  		if(editquyen == true){
							  			out.print("checked");
							  		}else{
							  			out.print("unchecked");
							  		}
							  	%>
							  	editquyen=<%=objCatPermission.getId() %>
							  >
								<div id="console-event"></div>
							</div>
		    			</div>
		    		</div>
		    	 </div>
		      </td>
		      <td>
		      	<div class="form-group">
		    		<div class="col-sm-7 col-md-7">
		    			<div class="input-group">
		    				<div class="form-check form-check-inline">
							  <input id="toggle-state" class="toggle-state-add toggle-state-delquyen" type="checkbox" data-toggle="toggle"
							  <%
							  		if(delquyen == true){
							  			out.print("checked");
							  		}else{
							  			out.print("unchecked");
							  		}
							  	%>
							  	delquyen=<%=objCatPermission.getId() %>
							  >
								<div id="console-event"></div>
							</div>
		    			</div>
		    		</div>
		    	 </div>
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
    <!-- /.content -->
			<%
		}
	}
%>
  </div>
  
  <script>
$(document).ready(function() {
	/* quyền thêm */
	$('.toggle-state-addquyen').change(function() {
	     var giatri = $(this).prop('checked')
	     var addquyen = $(this).attr("addquyen")
	     const swalWithBootstrapButtons = Swal.mixin({
			  customClass: {
			    confirmButton: 'btn btn-success',
			    cancelButton: 'btn btn-danger'
			  },
			  buttonsStyling: false
			})
	      swalWithBootstrapButtons.fire({
	    	  title: 'Bạn có chắc chắn?',
	    	  text: "Thao tác này sẽ ảnh hưởng đến tài khoản của bạn!",
	    	  icon: 'warning',
	    	  showCancelButton: true,
	    	  confirmButtonText: 'Tiếp tục',
	    	  cancelButtonText: 'Hủy bỏ',
	    	  reverseButtons: true
		 }).then((result) => {
			   if (result.isConfirmed) { 
				   $.ajax({
		            type: "POST",
		            url: "<%=request.getContextPath()%>/admin/permission/add",
		            data: {
		            	giatri : giatri,
		            	addquyen : addquyen
		            	},
		            context:this,
		            success: function (response) {
		              if(response == false)
		              {
		            	  swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Bạn không thể thay đổi trạng thái của đơn hàng.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
		              }else{
		            	  swalWithBootstrapButtons.fire(
	            			      'Thành công!',
	            			      'Thay đổi trạng thái thành công.',
	            			      'success'
	            			    ).then(function () {
  	    	                    location.reload();
  	    	                })
		              }
		            },
		            error: function (xhr, ajaxOptions, thrownError) {
		            	swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Thao tác không thể thực hiện.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
	    	            }
			   });
			   }else if (result.dismiss === Swal.DismissReason.cancel) {
	    		    swal.fire(
  	    		      'Thất bại',
  	    		      'Bạn đã hủy thao tác',
  	    		      'error'
  	    		    ).then(function () {
	                    location.reload();
	                })
  	    		  }
	    })
	})
	
	/* quyền sửa */
	$('.toggle-state-editquyen').change(function() {
	     var giatri = $(this).prop('checked')
	     var editquyen = $(this).attr("editquyen")
	     alert(giatri+"----"+editquyen)
	     const swalWithBootstrapButtons = Swal.mixin({
			  customClass: {
			    confirmButton: 'btn btn-success',
			    cancelButton: 'btn btn-danger'
			  },
			  buttonsStyling: false
			})
	      swalWithBootstrapButtons.fire({
	    	  title: 'Bạn có chắc chắn?',
	    	  text: "Thao tác này sẽ ảnh hưởng đến tài khoản của bạn!",
	    	  icon: 'warning',
	    	  showCancelButton: true,
	    	  confirmButtonText: 'Tiếp tục',
	    	  cancelButtonText: 'Hủy bỏ',
	    	  reverseButtons: true
		 }).then((result) => {
			   if (result.isConfirmed) { 
				   $.ajax({
		            type: "POST",
		            url: "<%=request.getContextPath()%>/admin/permission/edit",
		            data: {
		            	giatri : giatri,
		            	editquyen : editquyen
		            	},
		            context:this,
		            success: function (response) {
		              if(response == false)
		              {
		            	  swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Bạn không thể thay đổi trạng thái của đơn hàng.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
		              }else{
		            	  swalWithBootstrapButtons.fire(
	            			      'Thành công!',
	            			      'Thay đổi trạng thái thành công.',
	            			      'success'
	            			    ).then(function () {
  	    	                    location.reload();
  	    	                })
		              }
		            },
		            error: function (xhr, ajaxOptions, thrownError) {
		            	swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Thao tác không thể thực hiện.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
	    	            }
			   });
			   }else if (result.dismiss === Swal.DismissReason.cancel) {
	    		    swal.fire(
  	    		      'Thất bại',
  	    		      'Bạn đã hủy thao tác',
  	    		      'error'
  	    		    ).then(function () {
	                    location.reload();
	                })
  	    		  }
	    })
	})
	/* quyền xóa */
	$('.toggle-state-delquyen').change(function() {
	     var giatri = $(this).prop('checked')
	     var delquyen = $(this).attr("delquyen")
	     const swalWithBootstrapButtons = Swal.mixin({
			  customClass: {
			    confirmButton: 'btn btn-success',
			    cancelButton: 'btn btn-danger'
			  },
			  buttonsStyling: false
			})
	      swalWithBootstrapButtons.fire({
	    	  title: 'Bạn có chắc chắn?',
	    	  text: "Thao tác này sẽ ảnh hưởng đến tài khoản của bạn!",
	    	  icon: 'warning',
	    	  showCancelButton: true,
	    	  confirmButtonText: 'Tiếp tục',
	    	  cancelButtonText: 'Hủy bỏ',
	    	  reverseButtons: true
		 }).then((result) => {
			   if (result.isConfirmed) { 
				   $.ajax({
		            type: "POST",
		            url: "<%=request.getContextPath()%>/admin/permission/del",
		            data: {
		            	giatri : giatri,
		            	delquyen : delquyen
		            	},
		            context:this,
		            success: function (response) {
		              if(response == false)
		              {
		            	  swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Bạn không thể thay đổi trạng thái của đơn hàng.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
		              }else{
		            	  swalWithBootstrapButtons.fire(
	            			      'Thành công!',
	            			      'Thay đổi trạng thái thành công.',
	            			      'success'
	            			    ).then(function () {
  	    	                    location.reload();
  	    	                })
		              }
		            },
		            error: function (xhr, ajaxOptions, thrownError) {
		            	swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Thao tác không thể thực hiện.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
	    	            }
			   });
			   }else if (result.dismiss === Swal.DismissReason.cancel) {
	    		    swal.fire(
  	    		      'Thất bại',
  	    		      'Bạn đã hủy thao tác',
  	    		      'error'
  	    		    ).then(function () {
	                    location.reload();
	                })
  	    		  }
	    })
	})
	
});
</script>

  <!-- /.content-wrapper -->
  <%@include file="/templates/admin/inc/footer.jsp" %>