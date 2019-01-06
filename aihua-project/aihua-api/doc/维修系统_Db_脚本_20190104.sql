
-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
#主数据-零部件 零部件 实现增-删-改-查

CREATE TABLE `MDM_Components` (
	`Components_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '零部件ID',
	`Components_Code` varchar(50) DEFAULT NULL COMMENT '零件代码',
	`Components_ReplaceCode` varchar(50) DEFAULT NULL COMMENT '替代编码',
	`Components_Model` varchar(50) DEFAULT NULL COMMENT '零件型号',
	`Components_Name` varchar(50) NOT NULL COMMENT '零部件名称',
	`Components_BrandID` int(11) DEFAULT NULL COMMENT '零件所属品牌ID',
	`Components_TypeID` int(11) DEFAULT NULL COMMENT '零件类别ID',
	`Suggest_Price` DECIMAL(10,2) DEFAULT NULL COMMENT '建议零售价',
	`State` int(10) DEFAULT NULL COMMENT '零件状态 是否禁用',
	`CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
	`CREATED` datetime DEFAULT NULL COMMENT '创建时间',
	`UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
	`UPDATED` datetime DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (`Components_Id`),
	KEY `idx_MDM_Components_ComponentsCode` (`Components_Code`),
	KEY `idx_MDM_Components_ComponentsBrandID` (`Components_BrandID`),
	KEY `idx_MDM_Components_ComponentsTypeID` (`Components_TypeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#零部件库存表 库存粒度 零部件ID+库位ID 

CREATE TABLE `aiwa_Components_stock` (
	`Stock_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '库存ID',
	`Components_Id` int(11) NOT NULL COMMENT '零部件ID',
	`StockPlace_Id` int(11) NOT NULL COMMENT '零部件库位ID',
	`Stock_Quantity` int(11) NOT NULL COMMENT '库存数量',
	`CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
	`CREATED` datetime DEFAULT NULL COMMENT '创建时间',
	`UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
	`UPDATED` datetime DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (`Stock_Id`),
	KEY `idx_aiwa_Components_stock1` (`Components_Id`,`StockPlace_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#零部件出入库主表 (入库单、出库单 存在一张表中,用Bill_Type 区分 单据类型 1 为入库单| 0 为出库单)

CREATE TABLE `aiwa_Components_Stock_inout` (
	`Bill_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库|出库 单据ID',
	`Bill_Code` varchar(50) DEFAULT NULL COMMENT '单据编码',
	`Bill_Type` int(11) NOT NULL COMMENT '单据类型 1 为入库单| 0 为出库单',
	`inout_date` datetime DEFAULT NULL COMMENT '入库|出库 日期',
	`REMARK` varchar(50) DEFAULT NULL COMMENT '备注',
	`CREATED_BY` varchar(50) DEFAULT NULL COMMENT '创建人',
	`CREATED` datetime DEFAULT NULL COMMENT '创建时间',
	`UPDATED_BY` varchar(50) DEFAULT NULL COMMENT '修改人',
	`UPDATED` datetime DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY (`Bill_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#零部件出入库明细表 (入库数量为正、出库数量为负)

CREATE TABLE `aiwa_Components_Stock_inout_Detail` (
	`Detail_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库|出库 单据ID',
	`Bill_Id` int(11) NOT NULL  COMMENT '所属 入库|出库 单据ID',
	`Components_Id` int(11) NOT NULL COMMENT '零部件ID',
	`StockPlace_Id` int(11) NOT NULL COMMENT '零部件库位ID',
	`inout_Quantity` int(11) NOT NULL COMMENT '入库|出库数量',
	`memo` varchar(50) DEFAULT NULL COMMENT '备注',
	PRIMARY KEY (`Detail_Id`),
	KEY `idx_aiwa_Components_Stock_inout_Detail1` (`Bill_Id`)
	KEY `idx_aiwa_Components_Stock_inout_Detail2` (`Components_Id`,`StockPlace_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#维修单主表 - 维修单 增-删-改-查
#字段中文含义，见Excel 数据字典

CREATE TABLE `aiwa_service_order` (
	serviceId int(11) NOT NULL AUTO_INCREMENT,
	BillDate datetime NULL,
	CustomerName varchar(60) NULL,
	SexID int(11) NULL,
	CustomerTypeID int(11) NULL,
	Mobile varchar(60) NULL,
	Tel varchar(60) NULL,
	geoID int(11) NULL,
	Address varchar(60) NULL,
	Email varchar(60) NULL,
	ProdTypeNameSeriesID int(11) NULL,
	BalanceTypeID int(11) NULL,
	RepairTypeID int(11) NULL,
	ServiceTypeID int(11) NULL,
	MachineBrandModelID int(11) NULL,
	StorePlaceID int(11) NULL,
	WarrantyCard varchar(60) NULL,
	BuyDate datetime NULL,
	BuyShop varchar(60) NULL,
	MachineNo varchar(60) NULL,
	ReceiptNo varchar(60) NULL,
	OutMachineID varchar(60) NULL,
	BuyPrice varchar(60) NULL,
	SendGoodsNo varchar(60) NULL,
	FactoryID varchar(60) NULL,
	AppointmentDate datetime NULL,
	AppointmentID int(11) NULL,
	Appointment varchar(60) NULL,
	AppendixInfo varchar(60) NULL,
	BodyNote varchar(60) NULL,
	BrokenNote varchar(60) NULL,
	FrontPreview varchar(60) NULL,
	LoginNote varchar(60) NULL,
	STATE varchar(2) NULL,
	CREATED_BY varchar(50) NULL,
	CREATED datetime NULL,
	UPDATED_BY varchar(50) NULL,
	UPDATED datetime NULL,
	PRIMARY KEY (`serviceId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++