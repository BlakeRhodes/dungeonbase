using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace dungeonbase.Models
{
    public class Item
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("id")]
        public string Id { get; set; }

        [BsonElement("name")] 
        public string Name { get; set; }

        [BsonElement("description")] 
        public string Description { get; set; }

        [BsonElement("location")] 
        public string Location { get; set; }
    }
}