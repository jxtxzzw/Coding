class UndergroundSystem:

    def __init__(self):
        self.id_in_map = dict()
        self.station_time_map = dict()
        self.station_count_map = dict()
        

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.id_in_map[id] = (stationName, t)
        

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        (inStationName, inTime) = self.id_in_map[id]
        stations = (inStationName, stationName)
        if stations in self.station_time_map:
            self.station_time_map[stations] += (t - inTime)
            self.station_count_map[stations] += 1
        else:
            self.station_time_map[stations] = (t - inTime)
            self.station_count_map[stations] = 1

        
    def getAverageTime(self, startStation: str, endStation: str) -> float:
        stations = (startStation, endStation)
        return self.station_time_map[stations] / self.station_count_map[stations]
        


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
