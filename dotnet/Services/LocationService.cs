using System.Collections.Generic;
using System.Linq;
using dungeonbase.Domains;
using dungeonbase.Models;
using dungeonbase.Repositories;

namespace dungeonbase.Services
{
    public class LocationService
    {
        private readonly LocationRepository _locationRepository;

        public LocationService(LocationRepository locationRepository)
        {
            _locationRepository = locationRepository;
        }

        public Location FindById(string id)
        {
            var record = _locationRepository.Get(id);
            var related = GetRelated(record);
            
            return new Location
            {
                Id = record.Id,
                Description = record.Description,
                Name = record.Name,
                Related = related
            };
        }

        private List<LocationRecord> GetRelated(LocationRecord record)
        {
            return record.Related
                .Select(name => _locationRepository.GetByName(name))
                .ToList();
        }

        public List<Location> GetAll()
        {
            return _locationRepository.Get().Select(record =>
            {
                var related = GetRelated(record);
                return new Location
                {
                    Id = record.Id,
                    Description = record.Description,
                    Name = record.Name,
                    Related = related
                };
            }).ToList();
        }
    }
}