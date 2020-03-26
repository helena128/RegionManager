# Region Manager API
Region Manager service is a dedicated component for managing information about regions.


## Version: 0.1

**Contact information:**  
Elena Tcheprasova  
http://github.com/helena128  
elenatchepr@gmail.com  

### /regions

#### GET
##### Summary:

Retrieves all regions.

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | Successfully retrieved list of regions | [OperationResultWithRegionList](#operationresultwithregionlist) |
| default |  |  |

#### POST
##### Summary:

Add information about a new region.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| regionInput | body | Input information of the region for create/update operation. | Yes | [RegionInput](#regioninput) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 201 | Region was successfully created. | [Region](#region) |
| default |  |  |

### /regions/{id}

#### GET
##### Summary:

Retrieves region by ID.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | The ID of the region that should be retrieved/updated. | Yes | integer |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | Region was successfully retrieved. | [Region](#region) |
| default |  |  |

#### PUT
##### Summary:

Updates region by ID.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | The ID of the region that should be retrieved/updated. | Yes | integer |
| regionInput | body | Input information of the region for create/update operation. | Yes | [RegionInput](#regioninput) |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | Information about region was successfully updated. | [Region](#region) |
| default |  |  |

#### DELETE
##### Summary:

Removes region by ID.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | The ID of the region that should be retrieved/updated. | Yes | integer |

##### Responses

| Code | Description |
| ---- | ----------- |
| 204 | Information about region was successfully deleted. |
| default |  |

### Models


#### Region

Region.

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| Region |  | Region. |  |

#### RegionInput

Input information about region.

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| name | string | Name of the region. | Yes |
| shortName | string | Short name of the region. | Yes |

#### OperationResultWithRegionList

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| OperationResultWithRegionList | array |  |  |

#### ErrorModel

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| message | string | Message about error. | Yes |
| statusCode | integer | Optional field. May contain error code. | No |