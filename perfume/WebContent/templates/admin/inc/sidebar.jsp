<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
     <%
        if(session.getAttribute("userInfor") != null){
        	User user = (User) session.getAttribute("userInfor");
        	if(user.getCatUser().getId() < 6){
        	%>
    <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="<%=request.getContextPath()%>/admin" class="brand-link">
      <img src="<%=request.getContextPath()%>/templates/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">Perfume</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="<%=request.getContextPath()%>/templates/admin/dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
       
        <div class="info">
          <a href="javascript:void(0)" class="d-block"><%=user.getFullname() %></a>
        </div>
        
      </div>
      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
           <%
           	if(user.getCatUser().getId() < 5 ){
           		%>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Quản lý người dùng
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
            <%
            	if(user.getCatUser().getId() < 2){
            		%>
              <li class="nav-item">
                <a href="<%=request.getContextPath()%>/admin/permission" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Phân quyền</p>
                </a>
              </li>
            		<%
            	}
            %>
              <li class="nav-item">
                <a href="<%=request.getContextPath()%>/admin/categoriesuser" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Loại người dùng</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<%=request.getContextPath()%>/admin/user" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Người dùng</p>
                </a>
              </li>
            </ul>
          </li>
           		<%
           	}
           %>
          
          
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/banking" class="nav-link">
              <i class="nav-icon fas fa-comments-dollar"></i>
              <p>
                Quản lý tài chính
                <span class="right badge badge-danger">New</span>
              </p>
            </a>
          </li>
          
          <li class="nav-item">
            <a href="pages/widgets.html" class="nav-link">
              <i class="nav-icon fas fa-th"></i>
              <p>
                Quản lý nước hoa
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="<%=request.getContextPath()%>/admin/perfume" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Nước hoa</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<%=request.getContextPath()%>/admin/catperfume" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Thương hiệu</p>
                </a>
              </li>
            </ul>
          </li>
          
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/makup" class="nav-link">
              <i class="nav-icon fas fa-copy"></i>
              <p>
                Tư vấn làm đẹp
              </p>
            </a>
          </li>
         <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/order" class="nav-link">
              <i class="nav-icon fas fa-chart-pie"></i>
              <p>
               Quản lý đơn hàng
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/blog" class="nav-link">
              <i class="nav-icon fas fa-tree"></i>
              <p>
                Quản lý Blog
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/contact" class="nav-link">
              <i class="nav-icon fas fa-edit"></i>
              <p>
                Quản lý liên hệ
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/comment" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
                Quản lý comment
              </p>
            </a>
          </li>
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
  <%
        	}
        }
         %>