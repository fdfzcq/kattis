package main

import (
	"fmt"
)

func main() {
	var no_of_houses, no_of_cables int64
	fmt.Scanln(&no_of_houses, &no_of_cables)

	houses := make(map[int64]House)

	for i := 0; i < int(no_of_cables); i++ {
		var house_id, connected_house_id int64
		fmt.Scanln(&house_id, &connected_house_id)
		house := houses[house_id]
		if house.HouseId == 0 {
			house = House{house_id, []int64{}, false}
		}
		houses = house.Connect(houses, connected_house_id)
	}

	house_0 := houses[1]
	house_0.Connected = true
	houses[1] = house_0
	houses = house_0.UpdateConnection(houses)

	all_connected := true

	for i := 0; i < int(no_of_houses); i++ {
		house := houses[int64(i+1)]
		if !house.Connected {
			all_connected = false
			fmt.Printf("%d\n", i+1)
		}
	}

	if all_connected {
		fmt.Printf("%s\n", "Connected")
	}
}

func (house House) Connect(houses map[int64]House, connected_house_id int64) map[int64]House {
	house.ConnectedHouseIds = append(house.ConnectedHouseIds, connected_house_id)
	connected_house := houses[connected_house_id]
	connected_house.HouseId = connected_house_id
	connected_house.ConnectedHouseIds = append(connected_house.ConnectedHouseIds, house.HouseId)
	houses[house.HouseId] = house
	houses[connected_house_id] = connected_house
	return houses
}

func (house House) UpdateConnection(houses map[int64]House) map[int64]House {
	for _, connected_house_id := range house.ConnectedHouseIds {
		connected_house := houses[connected_house_id]

		if !connected_house.Connected {
			connected_house.Connected = true
			houses[connected_house.HouseId] = connected_house
			houses = connected_house.UpdateConnection(houses)
		}
	}

	return houses
}

type House struct {
	HouseId           int64
	ConnectedHouseIds []int64
	Connected         bool
}
