// Copy the code from the template. Declare a new type called hockey
// which embeds the sports type. Implement the matcher interface for hockey.
// When implementing the Search method for hockey, call into the Search method
// for the embedded sport type to check the embedded fields first. Then create
// two hockey values inside the slice of matchers and perform the search.
package main


import (
	"strings"
)


// matcher defines the behavior required for performing searches.
type matcher interface {
	Search(searchTerm string) bool
}

// sport represents a sports team.
type sport struct {
	team string
	city string
}

// Search checks the value for the specified term.
func (s sport) Search(searchTerm string) bool {
	if strings.Contains(s.team, searchTerm) ||
		strings.Contains(s.city, searchTerm) {
		return true
	}

	return false
}

// Declare a struct type named hockey that represents specific
// hockey information. Have it embed the sport type first.
type hockey struct{
	sp sport
}

// Implement the matcher interface for hockey.
func ( data hockey ) Search(searchTerm string) bool {
	// Make sure you call into Search method for the embedded
	// sport type.

	// Implement the search for the new fields.
	if strings.Contains(data.sp.team, searchTerm) ||
		strings.Contains(data.sp.city, searchTerm) {
		return true
	}

	return false
}

// main is the entry point for the application.
func main() {

	// Define the term to search.
	term := "Hokies"
	// Create a slice of matcher values to search.
	matches := make([]matcher, 3, 4)
	matches[0] = sport {
	"A", "B"}
	matches[1] = hockey{
	sp: sport{
	team:"Hokies", city: "Blacksburg",},
	}
	matches[2] = sport {
	"AA", "BB"}
	// Display what we are searching for.
	println("Searching for ",term)
	// Range of each matcher value and check the search term.
	for _, v := range matches {
		println(v.Search("Hockies"))
	}
	
}
