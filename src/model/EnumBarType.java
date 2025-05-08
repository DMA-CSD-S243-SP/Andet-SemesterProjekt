// Packages
package model;


/**
 * Represents the different available types of self-service bars
 * at any of Bone's restaurants. It is noteworthy that this could have
 * been done using a boolean check, but to account for future use of
 * the system we chose to use an enum, in case the restaurant chooses
 * to open for a new self-service bar solution.
 * 
 * 
 * @author Christoffer Søndergaard
 * @version 08-05-2025 - 13:34
 */
public enum EnumBarType
{
	SALADBAR,
	SOFTICEBAR
}