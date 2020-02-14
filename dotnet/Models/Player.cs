using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace dungeonbase.Models
{
    public class Player
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("id")]
        public string Id;
        
        [BsonElement("name")]
        public string Name;
        
        [BsonElement("location")]
        public string Location;
    }
}