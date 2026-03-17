import { z } from 'zod';

/**
 * Zod schema for the CreateDisplaySummaryRequest model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const createDisplaySummaryRequest = z.lazy(() => {
  return z.object({
    type: z.string().optional(),
    customText: z.string().optional(),
  });
});

/**
 *
 * @typedef  {CreateDisplaySummaryRequest} createDisplaySummaryRequest
 * @property {string} - The type of summary message to display (e.g., welcome, status, alert, info)
 * @property {string} - Optional custom text to append to the summary
 */
export type CreateDisplaySummaryRequest = z.infer<typeof createDisplaySummaryRequest>;

/**
 * Zod schema for mapping API responses to the CreateDisplaySummaryRequest application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplaySummaryRequestResponse = z.lazy(() => {
  return z
    .object({
      type: z.string().optional(),
      customText: z.string().optional(),
    })
    .transform((data) => ({
      type: data['type'],
      customText: data['customText'],
    }));
});

/**
 * Zod schema for mapping the CreateDisplaySummaryRequest application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplaySummaryRequestRequest = z.lazy(() => {
  return z
    .object({
      type: z.string().optional(),
      customText: z.string().optional(),
    })
    .transform((data) => ({
      type: data['type'],
      customText: data['customText'],
    }));
});
