package main

import "fmt"
import "strings"
import "bufio"
import "os"

func main() {
	fmt.Println("Input a string:")

	var s string

	scanner := bufio.NewScanner(os.Stdin)
    scanner.Scan()
	s = scanner.Text()
    if err := scanner.Err(); err != nil {
        os.Exit(1)
    }

	s = strings.ToLower(s)

	if (strings.HasPrefix(s, "i") && strings.HasSuffix(s, "n") && strings.Contains(s, "a")) {
		fmt.Println("Found!")
	} else {
		fmt.Println("Not Found!")
	}
}
