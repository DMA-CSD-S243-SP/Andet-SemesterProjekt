create table [Employee]
(
	employeeId int identity(100000,1) not null,
	firstName varchar(30) not null,
	lastName varchar(30) not null,
	jobTitle varchar(30) not null,
	systemAccessLevel int not null,

	primary key (employeeId),
);

create table [Restaurant]
(
	restaurantCode char(3) not null,
	[name] varchar(30) not null,
	city varchar(30) not null,
	streetName varchar(30) not null,

	primary key (restaurantCode),
);
--A dummy restaurant row is added.
insert into [Restaurant]	(restaurantCode, [name], city, streetName) 
values						('000',          '',     '',   ''        );

create table [Object_Table] --table is a keyword, and thus cannot be used as table name.
(
	restaurantCode char(3) not null default '000',
	tableOrderId int, --current TableOrder can be null, as a default TableOrder row would be very problematic.
	tableNumber char(4) not null,

	primary key (restaurantCode, tableNumber),
	constraint FK_Object_Table_Restaurant foreign key (restaurantCode) references Restaurant(restaurantCode) on delete cascade,
	--Table Order also has a foreign key constraint to Object_Table so it's made later, not here.
);

create table [TableOrder]
(
	tableOrderId int identity(100000, 1) not null,
	timeOfArrival datetime, --timeOfArrival can be null, as it signifies that it should be set when a customer arrives.
							--Orders with PersonalOrders should never have a null value in timeOfArrival.
	isTableOrderClosed bit not null,
	paymentType varchar(30) not null,
	totalTableOrderPrice decimal,
	totalAmountPaid decimal not null,
	isSentToKitchen bit not null,
	isRequestingService bit not null,
	orderPreparationTime int not null,
	employeeId int, -- Can be null, as a TableOrder shouldn't have an assigned employee the moment it's made.
	tableRestaurantCode char(3) not null default ('000'),
	tableNumber char(4) not null default '0000',

	primary key (tableOrderId),
	constraint FK_TableOrder_Employee  foreign key (employeeId) references Employee(employeeId) on delete set null,
	constraint FK_TableOrder_Restaurant foreign key (tableRestaurantCode) 
											references Restaurant(restaurantCode) on delete set default,
	constraint FK_TableOrder_Table_Object foreign key (tableRestaurantCode, tableNumber)
											references Object_Table(restaurantCode, tableNumber)
	--table nr 0 at restaurant '000' should be a dummy table, serving as a catch all for orphaned orders.
);

--TableOrder and Object_Table most mutually refer to one another. Either constraint require that the óther table exists
--therefor the constraint in the first made table is added after the second table is created.
alter table [Object_Table] add constraint [FK_Object_Table_TableOrder] 
foreign key (tableOrderId) references TableOrder(tableOrderId) --adds foreign key constraint to existing table.
on delete set null --If the current TableOrder is deleted, then the Table just be missing a current order.

--A dummy Object_Table row is made to give something for TableOrder to default to.
insert into [Object_Table]	(restaurantCode, tableNumber)
values						('000'         , 0);
--No table Order is added to table, aside from Object_Table: All dependencies of TableOrder cascade delete.

create table [MenuItem]
(
	menuItemId int identity(1,1) not null,
	[name] varchar(30) not null,
	[description] varchar(150) not null,
	preparationTime int not null,
	itemType varchar(30) not null,
	isMadeByKitchenStaff bit not null,

	primary key (menuItemId)
);

create table [PotatoDish]
(
	menuItemId int not null,
	isPremium bit not null,
	fixedPrice decimal not null,

	primary key (menuItemId),
	constraint FK_PotatoDish_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
);

create table [SelfServiceBar]
(
	menuItemId int not null,
	barType int not null,
	eveningPrice decimal not null,
	lunchPrice decimal not null,

	primary key (menuItemId),
	constraint FK_SelfServiceBar_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
);

create table [Drink]
(
	menuItemId int not null,
	isAlcoholic bit not null,
	price decimal not null,
	isRefill bit not null,

	primary key (menuItemId),
	constraint FK_Drink_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
);

create table [SideDish]
(
	menuItemId int not null,
	quantityPerServing int not null,
	fixedPrice decimal not null,

	primary key (menuItemId),
	constraint FK_SideDish_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
);

create table [DipsAndSauces]
(
	menuItemId int not null,
	isSauce bit not null,
	fixedPrice decimal not null,

	primary key (menuItemId),
	constraint FK_DipsAndSauces_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
);

create table [MainCourse]
(
	menuItemId int not null,
	introductionDescription varchar(120) not null,
	eveningPrice decimal not null,
	lunchPrice decimal not null,

	primary key (menuItemId),
	constraint FK_MainCourse_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
);

create table [AddOnOption]
(
	optionId int identity(1,1) not null,
	[description] varchar(60) not null,
	additionalPrice decimal not null,
	kitchenNotes varchar (15) not null,
	mainCourseId int not null,

	primary key (optionId),
	constraint FK_AddOnOption_MainCourse foreign key (mainCourseId) references MainCourse(menuItemId) on delete cascade,
);

create table [MultipleChoiceMenu]
(
	choiceMenuId int identity(1,1) not null,
	selectionDescription varchar(30) not null,
	mainCourseId int not null,

	primary key (choiceMenuId),
	constraint FK_MultipleChoiceMenu_MainCourse foreign key (mainCourseId) references MainCourse(menuItemId) on delete cascade
);

create table [SelectionOption]
(
	optionId int identity(1,1) not null,
	[description] varchar(60) not null,
	additionalPrice decimal not null,
	kitchenNotes varchar (15) not null,
	choiceMenuId int not null,

	primary key (optionId),
	constraint FK_SelectionOption_MainCourse foreign key (choiceMenuId) references MultipleChoiceMenu(choiceMenuId) on delete cascade,
);

create table [MenuCard]
(
	menuCardId int identity(1,1) not null,
	[name] varchar(30) not null,
	restaurantCode char(3) not null,

	primary key (menuCardId),
	constraint FK_MenuCard_Restaurant foreign key (restaurantCode) references Restaurant(restaurantCode),
);

create table [AvailabilityTracker]
(
	menuItemId int not null,
	menuCardId int not null,
	isAvailable bit not null,

	primary key (menuItemId, menuCardId),
	constraint FK_AvailabilityTracker_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete cascade,
	constraint FK_AvailabilityTracker_MenuCard foreign key (menuCardId) references MenuCard(menuCardId) on delete cascade,
);

create table [PersonalOrder]
(
	personalOrderId int identity(100000, 1) not null,
	customerAge int not null,
	customerName varchar(30) not null,
	tableOrderId int not null,

	primary key (personalOrderId),
	constraint FK_PersonalOrder_TableOrder foreign key (tableOrderId) references TableOrder(tableOrderId) on delete cascade,
	--A PersonalOrder should not exist without a TableOrder.
);

create table [PersonalOrderLine]
(
	personalOrderLineId int identity (100000,1) not null,
	additionalPrice decimal not null,
	notes varchar(60) not null,
	status int not null,
	personalOrderId int not null,
	menuItemId int, --orderline isn't removed when a MenuItem is deleted to preserve some historic data

	primary key (personalOrderLineId),
	constraint FK_PersonalOrderLine_PersonalOrder foreign key (personalOrderId) references PersonalOrder(personalOrderId) on delete cascade,
	constraint FK_PersonalOrderLine_MenuItem foreign key (menuItemId) references MenuItem(menuItemId) on delete set null
);

create table [Discount]
(
	discountId int identity(1,1) not null,
	[description] varchar(30) not null,

	primary key (discountId)
);

create table [DiscountPersonalOrder]
(
	personalOrderId int not null,
	discountId int not null,

	primary key (personalOrderId, discountId),
	constraint FK_DiscountPersonalOrder_PersonalOrder foreign key (personalOrderId) references PersonalOrder(personalOrderId) on delete cascade,
	constraint FK_DiscountPersonalOrder_Discount foreign key (discountId) references Discount(discountId) on delete cascade,
);