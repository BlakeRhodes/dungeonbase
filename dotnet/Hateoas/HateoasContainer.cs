using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace dungeonbase.Hateoas
{
    public abstract class HateoasContainer
    {
        
        
        [JsonPropertyName("_links")]
        public Dictionary<string, string> Links
        {
            get; set;
        } = new Dictionary<string, string>();

        protected void AddLink(string key, string value)
        {
            Links.Add(key, value);
        }
        protected abstract void AddSelf();
    }
}
