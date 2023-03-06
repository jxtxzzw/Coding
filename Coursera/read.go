package main

import (
	"fmt"
 	"bufio"
  	"os"
	"strings"
)

	
type name struct {
    first string
    last  string
}

func main() {

	names := make([]name, 0, 3)

	var filename string

	scanner := bufio.NewScanner(os.Stdin)

	fmt.Println("Please input your file name:")
	scanner.Scan()
	filename = scanner.Text()
	if err := scanner.Err(); err != nil {
		fmt.Println("Something wrong with the file name. Exit.")
		os.Exit(1)
	}

	// open file
    f, err := os.Open(filename)
    if err != nil {
		fmt.Println("Fail to open the file. Exit.")
		os.Exit(1)
    }

    // remember to close the file at the end of the program
    defer f.Close()

    // read the file line by line using scanner
    scanner = bufio.NewScanner(f)

    for scanner.Scan() {
        // do something with a line
		words := strings.Fields(scanner.Text())
		names = append(names, name{words[0], words[1]})
    }

    if err := scanner.Err(); err != nil {
		fmt.Println("Something wrong while reading. Exit.")
        os.Exit(1)
    }

	for _, name := range names {
        fmt.Printf("Name: %s %s\n", name.first, name.last)
    }


}
