INSERT INTO area(iAreaId,vAreaName) 
SELECT ProvinceID,ProvinceName FROM s_province;

INSERT INTO area(iParentAreaId,vAreaName) 
SELECT ProvinceID,CityName FROM s_city;

INSERT INTO area(iParentAreaId,vAreaName) 
SELECT a.iAreaId,sd.DistrictName FROM s_district sd,s_city sc,area a
WHERE sd.CityID = sc.CityID 
AND sc.CityName = a.vAreaName 
AND a.iAreaId >= 35