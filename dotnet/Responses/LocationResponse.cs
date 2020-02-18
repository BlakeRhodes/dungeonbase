using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using dungeonbase.Domains;
using dungeonbase.Hateoas;
using dungeonbase.Models;

namespace dungeonbase.Responses
{
    public class LocationResponse : HateaosContainer
    {
        public string Id { get; set; }

        [JsonPropertyName("name")] 
        public string Name { get; set; }

        [JsonPropertyName("description")] 
        public string Description { get; set; }

        [JsonPropertyName("related")] 
        public List<string> Related { get; set; }

        public LocationResponse(Location location)
        {
            Id = location.Id;
            Name = location.Name;
            Description = location.Description;
            
            AddLink("self", $"https://localhost:5001/v1/locations/{Id}");
            
            Related = location.Related.Select(relatedLocation =>
            {
                AddLink(relatedLocation.Name, $"https://localhost:5001/v1/locations/{relatedLocation.Id}");
                return relatedLocation;
            }).Select(relatedLocation => relatedLocation.Name).ToList();
            
            
        }
    }
}