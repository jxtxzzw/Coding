package main

import (
	"fmt"
	"bufio"
	"os"
	"strconv"
)

func ReadANumber() float64 {

	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()

	s := scanner.Text()
	if err := scanner.Err(); err != nil {
		os.Exit(1)
	}

	if num, err := strconv.ParseFloat(s, 64); err == nil {
		return num
	} else {
		fmt.Println("Error:", err)
		os.Exit(1)
	}

	return 0;

}


func GetInput() (float64, float64, float64) {
	

	fmt.Println("Please input acceleration (a):")
	a := ReadANumber()

	fmt.Println("Please input initial velocity (v0):")
	v0 := ReadANumber()

	fmt.Println("Please input initial displacement (s0):")
	s0 := ReadANumber()	

	return a, v0, s0

}



func GenFisplaceFn(a float64, v0 float64, s0 float64) func (float64) float64 {

	fmt.Printf("Generated a function with a = %f, v0 = %f, s0 = %f\n", a, v0, s0)

	return func (t float64) float64 {
		return 0.5 * a * t * t + v0 * t + s0 
	}

}

func main() {
	a, v0, s0 := GetInput()

	fn := GenFisplaceFn(a, v0, s0)

	fmt.Println("Please input the time (t):")
	t1 := ReadANumber()
	fmt.Printf("The displacement after %f second(s) is %f\n", t1, fn(t1))	

	fmt.Println("You can try one more time. Please input another time (t):")
	t2 := ReadANumber()
	fmt.Printf("The displacement after %f second(s) is %f\n", t2, fn(t2))

}
