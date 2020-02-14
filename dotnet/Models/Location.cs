﻿using System;
using System.Collections.Generic;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using RiskFirst.Hateoas.Models;

namespace dungeonbase.Models
{
    [BsonIgnoreExtraElements]
    [Serializable]
    public class Location : LinkContainer
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        [BsonElement("id")]
        public string Id { get; set; }

        [BsonElement("name")] public string Name { get; set; }

        [BsonElement("description")] public string Description { get; set; }

        [BsonElement("related")] public List<string> Related { get; set; }
        
    }
}