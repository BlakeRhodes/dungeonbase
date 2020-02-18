using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace dungeonbase.Hateoas
{
    public abstract class HateaosContainer
    {
        [JsonPropertyName("_links")] 
        public Dictionary<string, string> Links { get; set; } = new Dictionary<string, string>();
        
        public void AddLink(string key, string value)
        {
            Links.Add(key, value);
        }
    }
}