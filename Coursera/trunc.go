package main

import "fmt"
import "math"

func main() {
	fmt.Println("Input a number:")

	var num float64

	fmt.Scanf("%f", &num)

	fmt.Println(math.Trunc(num))
}
