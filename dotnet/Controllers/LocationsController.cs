using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Threading.Tasks;
using dungeonbase.Models;
using dungeonbase.Services;
using Microsoft.AspNetCore.Mvc;
using RiskFirst.Hateoas;

namespace dungeonbase.Controllers
{
    [ApiController]
    [Route("v1/" + "[controller]")]
    public class LocationsController : ControllerBase
    {
        private readonly LocationService _locationService;
        private readonly ILinksService _linksService;

        public LocationsController(
            LocationService locationService,
            ILinksService linksService
        )
        {
            _locationService = locationService;
            _linksService = linksService;
        }

        [HttpGet]
        public List<Location> Get()
        {
           var locations = _locationService.Get();
           return locations;
        }

        [HttpGet("{id}", Name = "GetLocationsRoute")]
        public async Task<Location> Get(string id)
        {
            var location = _locationService.Get(id);
            await _linksService.AddLinksAsync(location);
            return location;
        }
    }
}
