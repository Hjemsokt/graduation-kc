1.毕设系统架构分为三个模块
==========
rss(RecordSyncService 作为服务端来接收扫描到的记录，并处理收到的记录， 完成出库入库)
wms(WarehouseManageSystem 作为前台展示管理数据库数据，)
kc-Scanner(安卓端， 负责扫描货物的二维码， 并作为通信的客户端，将数据传输至服务端)


2.前期准备工作，数据库建库
==========
    参考wms/sql/createtable.sql分为两类
    a).一类为基本数据 
        创建供应商信息表   wms_supplier
        创建客户信息表     wms_customer
        创建货物信息表     wms_goods
        创建仓库信息表     wms_respository
        创建入库记录表     wms_record_in
        创建出库记录表     wms_record_out
        创建库存记录表     wms_record_storage



    b).另一类为运维数据，划分操作员权限、记录操作日志，有据可查
        创建系统用户信息表  wms_user
        创建用户角色表     wms_roles
        创建URL权限表      wms_action
        用户 - 角色关联表  wms_user_role
        角色 - URL权限关联表wms_role_action
        系统登入登出记录表 wms_access_record
        用户系统操作记录表 wms_operation_record
        创建仓库管理员信息表   wms_repo_admin
        
3.业务流程
========
    a).货物的二维码定义格式如下： 
           goodId^goodName^type^size^value^supplieId
                   
    b).入库流程：货物入库需要加上入库的仓库编码， 以适用于多仓库的管理；
                同时默认每次入库数量为1，事实上也是如此， 如果同一种货物批量入库，
                动态改变其入库数量；
                并不将数量写入到二维码基本数据中，减轻解码压力；
                仓库ID必须提前录入数据库， 否则无法识别；
                入库货物时， 先检查对应的goodId是否存在于数据库， 
                如果不存在， 需要插入货物信息表（wms_goods）和入库记录表（wms_record_in），
                修改库存记录表（wms_record_storage）， 这需要做事务控制， 要么同时成功， 用么失败之后全部回退；
                如果货物信息已经存在， 只需要插入入库记录表（wms_record_in）修改库存记录表（wms_record_storage）
        出库流程：货物出库需要加上客户ID(即经销商ID， 出于业务场景的需要)，
                 出库时， 货物必须存在货物信息表， 货物的储量必须大于出库的数量，
                 否则都会导致出库失败；
                 出库会插入出库记录表，并且修改库存记录表（wms_record_storage）,做事务控制
                 
               
     c）.模块启动顺序：先启动mysql数据库，启动tomat服务器， 再启动数据接收服务端(现在是test启动,可以考虑打包成jar 
     然后用service启动) ，最后才能使用app扫码功能
 ###服务组件构成图     
 ！[](tomcat启动.PNG)
 ！[](服务端启动.PNG)
     

4.WMS前台功能（下面的描述请用自己的话修改一下，可以自己搭配截图来呈现）
======      

* 系统操作权限管理。系统提供基本的登入登出功能，同时系统包含两个角色：系统超级管理员和普通管理员，超级管理员具有最高的操作权限，而普通管理员仅具有最基本的操作权限，而且仅能操作自己被指派的仓库。
* 请求URL鉴权。对于系统使用者登陆后进行操作发送请求的URL，后台会根据当前用户的角色判断是否拥有请求该URL的权限。
* 基础数据信息管理。对包括：货物信息、供应商信息、客户信息、仓库信息在内的基础数据信息进行管理，提供的操作有：添加、删除、修改、条件查询、导出为Excel和到从Excel导入。
* 仓库管理员管理。对仓库管理员信息CRUD操作，或者为指定的仓库管理员指派所管理的仓库。上述中的仓库管理员可以以普通管理员身份登陆到系统。
* 库存信息管理。对库存信息的CRUD操作，导入导出操作，同时查询的时候可以根据仓库以及商品ID等信息进行多条件查询。
* 基本仓库事务操作。执行货物的入库与出库操作。
* 系统登陆日志查询。超级管理员可以查询某一用户在特定时间段内的系统登陆日志。
* 系统操作日志查询。超级管理员可以查询某一用户在特定时间段内对系统进行操作的操作记录。、
* 密码修改

5.使用到的技术（前四点都可以在论文中重点阐述， 具体请google）
=====
* Socket通信技术
* 二维码识别技术
* MyBatis
* Spring MVC
* Apache Shiro
* Ehcache
* Log4j
* Slf4j
* Jackson
* C3P0
* MySQL-Connector
* jQuery
* Bootstrap