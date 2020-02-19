using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Transactions;
using dungeonbase.Domains;
using dungeonbase.Hateoas;
using dungeonbase.Models;

namespace dungeonbase.Responses
{
    public class LocationResponse : HateoasContainer
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
            
            AddSelf();
            
            Related = location.Related.Select(relatedLocation =>
            {
                AddLink(relatedLocation.Name, GetPath()+$"/{relatedLocation.Id}");
                return relatedLocation;
            }).Select(relatedLocation => relatedLocation.Name).ToList();
        }

        private string GetPath()
        {
            return "https://localhost:5001/v1/locations";
        }
        
        private string GetPathToSelf()
        {
            return GetPath() + $"/{Id}";
        }
        
        protected override void AddSelf()
        {
            AddLink("self", GetPathToSelf());
        }
    }
}