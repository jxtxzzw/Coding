package main

import (
	"fmt"
	"bufio"
	"os"
	"strconv"
	"strings"
)

func ValidateInput(s string) []int {
	nums := strings.Split(s, " ")

	var ss = make([]int, 0, 10)

	for i := 0; i < len(nums); i++ {
		var num, err = strconv.Atoi(nums[i])
		if (err != nil) {
			fmt.Println("Error:", err)
			return nil
		}
		if (len(ss) == 10) {
			fmt.Println("Error: Up to 10 intergers.")
			return nil
		}
		ss = append(ss, num)
	}

	return ss
}

func GetInput() []int {
	var s string

	fmt.Println("Please input a list of integers, split by space, and up to 10 intergers:")
	scanner := bufio.NewScanner(os.Stdin)

	scanner.Scan()
	s = scanner.Text()
	if err := scanner.Err(); err != nil {
		os.Exit(1)
	}

	rawNum := ValidateInput(s)

	if (rawNum == nil) {
		fmt.Println("Error: You must input a list of integers, split by space, and up to 10 intergers.")
		os.Exit(1)
	} else {
		return rawNum
	}

	return nil
}

func Swap(num []int, i int, j int) {
	tmp := num[i]
	num[i] = num[j]
	num[j] = tmp
}

func BubbleSort(num []int) {
	l := len(num)

	for i := 0; i < l; i++ {
		for j := 0; j < i; j++ {
			if (num[i] < num[j]) {
				Swap(num, i, j)
			}
		}
	}
}


func main() {
	num := GetInput()

	fmt.Printf("The Original Data Is: %v\n", num)
	BubbleSort(num)
	fmt.Printf("The Sorted Data Is: %v\n", num)

}
