using System.Collections.Generic;
using System.Linq;
using dungeonbase.Responses;
using dungeonbase.Services;
using Microsoft.AspNetCore.Mvc;

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
        public List<LocationResponse> Get()
        {
            var locations = _locationService.GetAll().Select(record => new LocationResponse(record)).ToList();
            
            return locations;
        }

        [HttpGet]
        [Route("{id}")]
        public LocationResponse Get(string id)
        {
            var location = _locationService.FindById(id);
            return new LocationResponse(location);
        }
    }
}