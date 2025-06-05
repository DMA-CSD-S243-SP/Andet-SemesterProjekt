// Packages
package model;


/**
 * Represents the different available types of self-service bars
 * at any of Bone's restaurants. It is noteworthy that this could have
 * been done using a boolean check in the program's current state,
 * but to account for future usage and development of the system it was
 * chosen to use an enumerator, in case the restaurant chooses to add
 * a new type of self-service bar to their menu.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 01/03/2025 - 13:34
 */
public enum EnumBarType
{
	SALADBAR,
	SOFTICEBAR
}