/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018-05-09-ÐÇÆÚÈý 20:43:31                      */
/*==============================================================*/


drop table if exists AdminMenu;

drop table if exists AdminPerm;

drop table if exists AdminRole;

drop table if exists AdminRolePerm;

drop table if exists AdminUser;

drop table if exists AdminUserRole;

drop table if exists Area;

drop table if exists CommentInfo;

drop table if exists CommentReply;

drop table if exists CommentReportRecord;

drop table if exists CosmeticClass;

drop table if exists MessageInfo;

drop table if exists MessageStatus;

drop table if exists ProductBrand;

drop table if exists ProductEffect;

drop table if exists ProductInfo;

drop table if exists ProductPicture;

drop table if exists ProductProperty;

drop table if exists ReplyReportRecord;

drop table if exists UserFollowClass;

drop table if exists UserFollowProduct;

drop table if exists UserFollowUser;

drop table if exists UserInfo;

drop table if exists UserReportRecord;

drop table if exists UserUpRecord;

/*==============================================================*/
/* Table: AdminMenu                                             */
/*==============================================================*/
create table AdminMenu
(
   iMenuId              int not null auto_increment,
   iPermId              int,
   vMenuName            varchar(50),
   iIsRootNavi          int,
   iParentMenuId        int,
   vUrl                 varchar(50),
   iMenuOrder           int,
   primary key (iMenuId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: AdminPerm                                             */
/*==============================================================*/
create table AdminPerm
(
   iPermId              int not null auto_increment,
   vPermName            varchar(30),
   vPermDesc            varchar(100),
   vPermCode            varchar(20),
   primary key (iPermId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: AdminRole                                             */
/*==============================================================*/
create table AdminRole
(
   iRoleId              int not null auto_increment,
   vRoleName            varchar(30),
   vRoleDesc            varchar(100),
   vRoleCode            varchar(20),
   primary key (iRoleId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: AdminRolePerm                                         */
/*==============================================================*/
create table AdminRolePerm
(
   iRolePermId          int not null,
   iRoleId              int,
   iPermId              int,
   primary key (iRolePermId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: AdminUser                                             */
/*==============================================================*/
create table AdminUser
(
   vAdminUser           varchar(20) not null,
   vAdminPassword       varchar(32),
   cPasswordSalt        char(5),
   isSa                 int,
   primary key (vAdminUser)
);

/*==============================================================*/
/* Table: AdminUserRole                                         */
/*==============================================================*/
create table AdminUserRole
(
   iUserRoleId          int not null auto_increment,
   vAdminUser           varchar(20),
   iRoleId              int,
   primary key (iUserRoleId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: Area                                                  */
/*==============================================================*/
create table Area
(
   iAreaId              int not null auto_increment,
   iParentAreaId        int,
   vAreaName            varchar(40),
   primary key (iAreaId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: CommentInfo                                           */
/*==============================================================*/
create table CommentInfo
(
   bCommentId           bigint not null auto_increment,
   bUserId              bigint,
   bProductId           bigint,
   vArticleTitle        varchar(100),
   tContent             text,
   vArticlaMark         varchar(50),
   fWorthMark           float,
   vBuyWay              varchar(4),
   dSendTime            datetime,
   iArticleState        int,
   iReadCount           int,
   iLoveCount           int,
   iUpCount             int,
   primary key (bCommentId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: CommentReply                                          */
/*==============================================================*/
create table CommentReply
(
   bReplyId             bigint not null auto_increment,
   bArticleId           bigint,
   bParentCommentId     bigint,
   bUserId              bigint,
   vReplyDetail         varchar(200),
   dReplyTime           datetime,
   iReportCount         int,
   primary key (bReplyId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: CommentReportRecord                                   */
/*==============================================================*/
create table CommentReportRecord
(
   bReportRecordId      bigint not null auto_increment,
   bCommentId           bigint,
   bUserId              bigint,
   vReportContent       varchar(100),
   dReportTime          datetime,
   primary key (bReportRecordId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: CosmeticClass                                         */
/*==============================================================*/
create table CosmeticClass
(
   bClassId             int not null auto_increment,
   vClassName           varchar(30),
   primary key (bClassId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: MessageInfo                                           */
/*==============================================================*/
create table MessageInfo
(
   lMessageId           bigint not null auto_increment,
   bUserId              bigint,
   vContent             varchar(200),
   cMessageType         char(4),
   dMessageTime         datetime,
   primary key (lMessageId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: MessageStatus                                         */
/*==============================================================*/
create table MessageStatus
(
   lStatusId            bigint not null auto_increment,
   bUserId              bigint,
   lMessageId           bigint,
   cMessageStatus       char(4),
   primary key (lStatusId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: ProductBrand                                          */
/*==============================================================*/
create table ProductBrand
(
   iBrandId             int not null auto_increment,
   vBrandName           varchar(100),
   primary key (iBrandId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: ProductEffect                                         */
/*==============================================================*/
create table ProductEffect
(
   iEffectId            int not null auto_increment,
   vEffectName          varchar(30),
   primary key (iEffectId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: ProductInfo                                           */
/*==============================================================*/
create table ProductInfo
(
   bProductId           bigint not null auto_increment,
   vProductName         varchar(50),
   iBrand               int,
   iClassify            int,
   iProperty            int,
   iEffect              int,
   vSkinTexture         varchar(10),
   vSpec                varchar(20),
   dComeInDate          date,
   fReferencePrice      float,
   tDesc                text,
   fWorthCount          float,
   bCommentCount        bigint,
   bBrowserCount        bigint,
   bFollowCount         bigint,
   primary key (bProductId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: ProductPicture                                        */
/*==============================================================*/
create table ProductPicture
(
   bPicId               bigint not null auto_increment,
   bProductId           bigint,
   vPictureUrl          varchar(50),
   primary key (bPicId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: ProductProperty                                       */
/*==============================================================*/
create table ProductProperty
(
   iPropertyId          int not null auto_increment,
   vPropertyName        varchar(20),
   primary key (iPropertyId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: ReplyReportRecord                                     */
/*==============================================================*/
create table ReplyReportRecord
(
   bRecordId            bigint not null auto_increment,
   bReplyId             bigint,
   bUserId              bigint,
   primary key (bRecordId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: UserFollowClass                                       */
/*==============================================================*/
create table UserFollowClass
(
   bUserClassId         bigint not null auto_increment,
   bClassId             int,
   bUserId              bigint,
   primary key (bUserClassId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: UserFollowProduct                                     */
/*==============================================================*/
create table UserFollowProduct
(
   bUserProductId       bigint not null auto_increment,
   bProductId           bigint,
   bUserId              bigint,
   dFollowTime          datetime,
   primary key (bUserProductId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: UserFollowUser                                        */
/*==============================================================*/
create table UserFollowUser
(
   bUserUserId          bigint not null auto_increment,
   bUserId              bigint,
   bFollowedUserId      bigint,
   primary key (bUserUserId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: UserInfo                                              */
/*==============================================================*/
create table UserInfo
(
   bUserId              bigint not null auto_increment,
   vUserName            varchar(20),
   vPassword            varchar(32),
   cPasswordSalt        char(6),
   vNickname            varchar(30),
   cGender              char(2),
   vSkinTexture         varchar(4),
   iBornYear            int,
   iUserAreaId          int,
   vIntroduction        varchar(200),
   vPortrait            varchar(50),
   cPhone               char(11),
   vEmail               varchar(50),
   cActivationCode      char(32),
   vUserState           varchar(4),
   iMembershipPoint     int,
   iMemberLevel         int,
   dRegisterTime        datetime,
   dLastLoginTime       datetime,
   primary key (bUserId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: UserReportRecord                                      */
/*==============================================================*/
create table UserReportRecord
(
   bReportId            bigint not null auto_increment,
   bUserId              bigint,
   bReportUserId        bigint,
   vReportDetail        varchar(100),
   dReportTime          datetime,
   primary key (bReportId)
)
auto_increment = 1;

/*==============================================================*/
/* Table: UserUpRecord                                          */
/*==============================================================*/
create table UserUpRecord
(
   bUpRecordId          bigint not null auto_increment,
   bCommentId           bigint,
   bUserId              bigint,
   dUpTime              datetime,
   primary key (bUpRecordId)
)
auto_increment = 1;

