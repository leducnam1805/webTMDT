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
            <h1 class="m-0">Admin</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="javascript:void(0)">Trang chủ</a></li>
              <li class="breadcrumb-item active">Admin</li>
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
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-info">
              <div class="inner">
              <%
              	if(request.getAttribute("countPer") != null){
              		int countPer = (Integer) request.getAttribute("countPer");
              		%>
              			<h3><%=countPer%></h3>
              		<%
              	}
              %>
                
                <p>Danh sách sản phẩm</p>
              </div>
              <div class="icon">
                <i class="ion ion-bag"></i>
              </div>
              <a href="<%=request.getContextPath()%>/admin/perfume" class="small-box-footer">Đi tới&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-success">
              <div class="inner">
              <%
              	if(request.getAttribute("countOrder") != null){
              		int countOrder = (Integer) request.getAttribute("countOrder");
              		%>
              			<h3><%=countOrder%></h3>
              		<%
              	}
              %>
                <p>Danh sách đơn hàng</p>
              </div>
              <div class="icon">
                <i class="ion ion-stats-bars"></i>
              </div>
              <a href="<%=request.getContextPath()%>/admin/order" class="small-box-footer">Đi tới&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-warning">
              <div class="inner">
              <%
              	if(request.getAttribute("countUser") != null){
              		int countUser = (Integer) request.getAttribute("countUser");
              		%>
              			<h3><%=countUser%></h3>
              		<%
              	}
              %>
                <p>Quản lý người dùng</p>
              </div>
              <div class="icon">
                <i class="ion ion-person-add"></i>
              </div>
              <a href="<%=request.getContextPath()%>/admin/user" class="small-box-footer">Đi tới&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
          <div class="col-lg-3 col-6">
            <!-- small box -->
            <div class="small-box bg-danger">
              <div class="inner">
              <%
              	if(request.getAttribute("countContact") != null){
              		int countContact = (Integer) request.getAttribute("countContact");
              		%>
              			<h3><%=countContact%></h3>
              		<%
              	}
              %>
                <p>Quản lý liên hệ</p>
              </div>
              <div class="icon">
                <i class="ion ion-pie-graph"></i>
              </div>
              <a href="<%=request.getContextPath()%>/admin/contact" class="small-box-footer">Đi tới&nbsp;<i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          <!-- ./col -->
        </div>
        <!-- /.row -->
        <!-- Main row -->
        
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <%@include file="/templates/admin/inc/footer.jsp" %>