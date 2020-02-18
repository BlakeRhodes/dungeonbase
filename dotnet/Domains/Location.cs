using System.Collections.Generic;
using dungeonbase.Models;

namespace dungeonbase.Domains
{
    public class Location
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public List<LocationRecord> Related { get; set; }
    }
}