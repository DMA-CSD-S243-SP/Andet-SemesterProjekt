-- All tables are deleted in reverse order of how they were added.
drop table [DiscountPersonalOrder];
GO
drop table [Discount];
GO
drop table [PersonalOrderLine];
GO
drop table [PersonalOrder];
GO
drop table [AvailabilityTracker];
GO
drop table [SelectionOption];
GO
drop table [MultipleChoiceMenu];
GO
drop table [AddOnOption];
GO
drop table [MainCourse];
GO
drop table [DipsAndSauces];
GO
drop table [SideDish];
GO
drop table [Drink];
GO
drop table [SelfServiceBar];
GO
drop table [PotatoDish];
GO
drop table [MenuItem];
GO
drop table [MenuCard];
GO
-- Constraint has to be dropped to be able to delete TableOrder.
alter table [Object_Table] drop constraint [FK_Object_Table_TableOrder];
GO
drop table [TableOrder];
GO
drop table [Object_Table];
GO
drop table [Restaurant];
GO
drop table [Employee];