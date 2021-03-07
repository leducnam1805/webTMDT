<%@page import="models.CatUser"%>
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
            <h1 class="m-0">Loại tài khoản</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Loại tài khoản</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
		<%
        	if(request.getAttribute("err") != null){
        		%>
        			<div class="alert alert-danger" role="alert">
						  ${err}
					</div>
        		<%
        	}
        %>
	<%
		if(request.getAttribute("catUser") != null){
			CatUser catUser = (CatUser) request.getAttribute("catUser");
			int id =  catUser.getId();
			String nameCU = catUser.getCatUser();
			%>
				<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
           <div class="col-md-12">
                <h2>Sửa loại tài khoản</h2>
            </div>
        </div>
        <div class="row">
	        <div class="col-md-12">
	        	<div class="card">
			    	<div class="card-body">
			    		<div class="col-md-12">
			                <form role="form" method="post"id="form" action="<%=request.getContextPath()%>/admin/categoriesuser/edit">
			                	<div class="form-group">
			                        <input type="hidden" id="id" value="<%=id %>" name="id" class="form-control" />
			                    </div>
			                    <div class="form-group">
			                        <label for="name">Tên loại tài khoản</label>
			                        <input type="text" id="name" value="<%=nameCU %>" name="catUser" class="form-control" />
			                    </div>
			                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
			                </form>
			            </div>
			    	</div>
			  	</div>
	        </div>
        </div>
        <!-- Main row -->
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
			<%
		}
	%>
    
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@include file="/templates/admin/inc/footer.jsp" %>