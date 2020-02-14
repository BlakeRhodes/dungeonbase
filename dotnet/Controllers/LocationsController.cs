using System.Collections.Generic;
using System.Threading.Tasks;
using dungeonbase.Models;
using dungeonbase.Services;
using Microsoft.AspNetCore.Mvc;

namespace dungeonbase.Controllers
{
    [ApiController]
    [Route("v1/" + "[controller]")]
    public class LocationsController : ControllerBase
    {
        private readonly LocationService _locationService;

        public LocationsController(
            LocationService locationService
        )
        {
            _locationService = locationService;
        }

        [HttpGet]
        public List<LocationRecord> Get()
        {
           var locations = _locationService.Get();
           return locations;
        }

        [HttpGet("{id}", Name = "GetLocationsRoute")]
        public async Task<LocationRecord> Get(string id)
        {
            var location = _locationService.Get(id);
            return location;
        }
    }
}
