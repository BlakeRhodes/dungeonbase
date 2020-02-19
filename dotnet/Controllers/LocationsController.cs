using System.Collections.Generic;
using System.Linq;
using dungeonbase.Responses;
using dungeonbase.Services;
using Microsoft.AspNetCore.Mvc;
using Swashbuckle.AspNetCore.Annotations;

namespace dungeonbase.Controllers
{
    [ApiController]
    [Route("v1/" + "[controller]")]
    public class LocationsController : ControllerBase
    {
        private readonly LocationService _locationService;

        public LocationsController
        (
            LocationService locationService
        )
        {
            _locationService = locationService;
        }

        [HttpGet]
        [SwaggerOperation(
            Summary = "Returns a List of all Locations",
            Description = "This returns all of the locations in the database as a list.",
            OperationId = "Get"
        )]
        public List<LocationResponse> Get()
        {
            var locations = _locationService.GetAll().Select(record => new LocationResponse(record)).ToList();
            
            return locations;
        }

        [HttpGet]
        [SwaggerOperation(
            Summary = "Returns a Location.",
            Description = "This returns a location with the id that is passed to it.",
            OperationId = "Get"
        )]
        [HttpGet]
        [Route("{id}")]
        public LocationResponse Get(string id)
        {
            var location = _locationService.FindById(id);
            return new LocationResponse(location);
        }
    }
}