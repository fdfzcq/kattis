#!/usr/bin/ruby

def init_house(id)
  house = @houses[id] == nil ? [[], false] : @houses[id]
  @houses[id] = house
  return house
end

def update_connection(house)
  house[0].each do |connected_house_id|
    connected_house = @houses[connected_house_id]

    if !connected_house[1]
      connected_house[1] = true
      @houses[connected_house_id] = connected_house
      update_connection(connected_house)
    end
  end
end

def read_values()
  STDIN.each_line do |line|
    return line.scan(/\d+/).map(&:to_i)
  end
end

values = read_values()
no_of_houses = values[0]
no_of_cables = values[1]

@houses = Array.new()

(1..no_of_cables).each do |i|
  inputs = read_values()
  house_id = inputs[0]
  connected_house_id = inputs[1]
  house = init_house(house_id)
  house[0] << connected_house_id
  connected_house = init_house(connected_house_id)
  connected_house[0] << house_id
end

house_0 = init_house(1)
house_0[1] = true

update_connection(house_0)

all_connected = true

(1..no_of_houses).each do |i|
  if @houses[i] == nil || @houses[i][1] == false
    all_connected = false
    puts i
  end
end

puts "Connected" if all_connected
