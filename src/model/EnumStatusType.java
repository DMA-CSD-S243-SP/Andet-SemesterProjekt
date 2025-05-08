package model;

/**
 * Represents the different available types of status in PersonalOrderLine
 * at any of Bone's restaurants.  
 * 
 * @author Line Bertelsen
 * @version 08-05-2025 - 16.50
 */

public enum EnumStatusType
{
	NOTORDEREDYET,
	WAITINGTOBEPREPARED,
	READYTOBESERVED,
	ALREADYSERVED,
	CANCELLEDBYSTAFF
}