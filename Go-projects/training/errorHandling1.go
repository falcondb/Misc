// Create two error variables, one called ErrInvalidValue and the other
// called ErrTooLarge. Provide the static message for each variable.
// Then write a function called checkAmount that accepts a float64 type value
// and returns an error value. Check the value for zero and if it is, return
// the ErrInvalidValue. Check the value for greater than $1,000 and if it is,
// reutrn the ErrAmountToLarge. Write a main function to call the checkAmount
// function and check the return error value. Display a proper message to the screen.
package main

// Add imports.
import (
	"errors"
)

// Declare an error variable named ErrInvalidValue using the New
// function from the errors package.
var ErrInvalidValue = errors.New("ErrInvalidValue")

// Declare an error variable named ErrAmountToLarge using the New
// function from the errors package.
var ErrAmountToLarge = errors.New("ErrAmountToLarge")

// Declare a function named checkAmount that accepts a value of
// type float64 and returns an error interface value.

func checkAmount(amt float64) error {
	// Is the parameter equal to zero. If so then return
	// the error variable.
	if amt <= 0 {
		return ErrInvalidValue
	}
	// Is the parameter greater than 1000. If so then return
	// the other error variable.
	if amt > 1000 {
		return ErrAmountToLarge
	}
	// Return nil for the error value.
	return nil
}

// main is the entry point for the application.
func main() {
	// Call the checkAmount function and check the error. Then
	// use a switch/case to compare the error with each variable.
	// Add a default case. Return if there is an error.
	myerr := checkAmount(0)
	// Display everything is good.
	println(0, "\t", myerr.Error())

	myerr = checkAmount(100000)
	// Display everything is good.
	println(100000, "\t", myerr.Error())

	myerr = checkAmount(100)
	if myerr == nil {
		println("everything is good")
	} else {
		// Display everything is good.

		println(100, "\t", myerr.Error())
	}
}
