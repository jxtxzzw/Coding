package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
)

var predefinedAnimals []string = []string {"cow", "bird", "snake"}
var predefinedInformation []string = []string {"eat", "move", "speak"}
var predefinedPrompt string = "Invalid Input: Every request from the user must be a single line containing 2 strings. The first string is the name of an animal, either “cow”, “bird”, or “snake”. The second string is the name of the information requested about the animal, either “eat”, “move”, or “speak”."

func validateInput(ss []string) bool {
	correctNum := len(ss) == 2
	correctAnimal := false
	correctInfo := false
	if (correctNum) {
		for _, v := range predefinedAnimals {
			if (v == ss[0]) {
				correctAnimal = true
			}
		}
		for _, v := range predefinedInformation {
			if (v == ss[1]) {
				correctInfo = true
			}
		}
	}
	return (correctNum &&  correctAnimal && correctInfo)
}

type Animal struct {
	eat string
	move string
	speak string
}

func (animal *Animal) Eat() string {
	return animal.eat
}

func (animal *Animal) Move() string {
	return animal.move
}

func (animal *Animal) Speak() string {
	return animal.speak
}

func (animal *Animal) Init(eat string, move string, speak string) *Animal {
	animal.eat = eat
	animal.move = move
	animal.speak = speak
	return animal
}

func main() {
	animals := make(map[string] *Animal)
	animals["cow"] = new(Animal).Init("grass", "walk", "moo")
	animals["bird"] = new(Animal).Init("worms", "fly", "peep")
	animals["snake"] = new(Animal).Init("mice", "slither", "hsss")

	scanner := bufio.NewScanner(os.Stdin)

	for {
		fmt.Print("> ")
		
		scanner.Scan()
		s := scanner.Text()
		if err := scanner.Err(); err != nil {
			fmt.Printf("%s\n", predefinedPrompt)
			os.Exit(1)
		}

		ss := strings.Split(s, " ")

		if (validateInput(ss)) {
			animal := animals[ss[0]]
			ans := ""

			switch (ss[1]) {
			case "eat":
				ans = animal.Eat()
			case "move":
				ans = animal.Move()
			case "speak":
				ans = animal.Speak()
			}

			fmt.Printf("%s - %s: %s\n", ss[0], ss[1], ans)

		} else {
			fmt.Printf("%s\n", predefinedPrompt)
		}
		
	}
}
